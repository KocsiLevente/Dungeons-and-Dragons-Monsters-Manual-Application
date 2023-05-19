package com.example.dungeonsanddragonsmonstersmanualapplication.utils

import android.graphics.BitmapFactory
import com.example.dungeonsanddragonsmonstersmanualapplication.models.MonsterDetailsResult
import com.example.dungeonsanddragonsmonstersmanualapplication.models.MonsterElement
import com.example.dungeonsanddragonsmonstersmanualapplication.models.MonstersResult
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

object TesterUtils {

    //Only for testing.
    fun testAddMonster(listToEdit: MutableList<MonsterElement>, monster: MonsterElement) {
        listToEdit.add(monster)
    }

    //Only for testing.
    fun testGetMonster(listToEdit: MutableList<MonsterElement>, index: String): MonsterElement? {
        return listToEdit.find { m -> m.index == index }
    }

    //Only for testing.
    fun testGetMonsters(listToEdit: MutableList<MonsterElement>) {
        listToEdit.add(MonsterElement("Test1", "Test1", "Test1"))
    }

    //Only for testing.
    fun testUpdateMonster(listToEdit: MutableList<MonsterElement>, monster: MonsterElement) {
        listToEdit.removeAll { m -> m.index == monster.index }
        listToEdit.add(monster)
    }

    //Only for testing.
    fun testDeleteMonster(listToEdit: MutableList<MonsterElement>, monster: MonsterElement) {
        listToEdit.remove(monster)
    }

    //Only for testing.
    fun testGetMonsterDetailsCall(assertSuccess: () -> Unit, assertFailed: () -> Unit) {
        val retrofit = InjectorUtils.getRetrofitObject()
        val dndApi = InjectorUtils.createDnDApiInterface(retrofit)
        val call = dndApi.getMonsterDetails("bandit")

        call.enqueue(object : Callback<MonsterDetailsResult> {
            override fun onFailure(call: Call<MonsterDetailsResult>?, t: Throwable?) {
                assertFailed()
            }

            override fun onResponse(call: Call<MonsterDetailsResult>?, response: Response<MonsterDetailsResult>?) {
                if (!response!!.isSuccessful) {
                    assertFailed()
                }
                else {
                    val monsterDetails = response.body()
                    if (monsterDetails != null) {
                        assertSuccess()
                    }
                    else {
                        assertFailed()
                    }
                }
            }
        })
    }

    fun testGetMonsterImageCall(assertSuccess: () -> Unit, assertFailed: () -> Unit) {
        val retrofit = InjectorUtils.getRetrofitObject()
        val dndApi = InjectorUtils.createDnDApiInterface(retrofit)
        val call = dndApi.getMonsterImage("aboleth")

        call.enqueue(object : Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>?, t: Throwable?) {
                assertFailed()
            }

            override fun onResponse(call: Call<ResponseBody>?, response: Response<ResponseBody>?) {
                if (!response!!.isSuccessful) {
                    assertFailed()
                }
                else {
                    try {
                        val inputStream = response.body()!!.byteStream()
                        BitmapFactory.decodeStream(inputStream)
                        assertSuccess()
                    } catch (e: Exception) {
                        assertFailed()
                    }
                }
            }
        })
    }

    //Only for testing.
    fun testGetMonstersCall(assertSuccess: () -> Unit, assertFailed: () -> Unit) {
        val retrofit = InjectorUtils.getRetrofitObject()
        val dndApi = InjectorUtils.createDnDApiInterface(retrofit)
        val call = dndApi.getMonsters()

        call.enqueue(object : Callback<MonstersResult> {
            override fun onFailure(call: Call<MonstersResult>?, t: Throwable?) {
                assertFailed()
            }

            override fun onResponse(call: Call<MonstersResult>?, response: Response<MonstersResult>?) {
                if (!response!!.isSuccessful) {
                    assertFailed()
                }
                else {
                    val monsters = response.body()
                    if (monsters != null) {
                        assertSuccess()
                    }
                    else {
                        assertFailed()
                    }
                }
            }
        })
    }
}