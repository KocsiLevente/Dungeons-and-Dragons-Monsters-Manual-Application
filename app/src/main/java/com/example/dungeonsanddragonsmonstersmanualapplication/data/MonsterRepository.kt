package com.example.dungeonsanddragonsmonstersmanualapplication.data

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import androidx.lifecycle.LiveData
import com.example.dungeonsanddragonsmonstersmanualapplication.models.Monster
import com.example.dungeonsanddragonsmonstersmanualapplication.models.MonsterDetailsResult
import com.example.dungeonsanddragonsmonstersmanualapplication.models.MonsterElement
import com.example.dungeonsanddragonsmonstersmanualapplication.models.MonstersResult
import com.example.dungeonsanddragonsmonstersmanualapplication.utils.InjectorUtils
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception
import java.util.*


class MonsterRepository private constructor(private val monsterDao: MonsterDao) {

    fun addMonster(listToEdit: MutableList<MonsterElement>, monster: MonsterElement) {
        //Mocked layer.
        monsterDao.addMonster(monster)
        listToEdit.plus(monster)
    }

    fun getMonsterDetails(toEdit: (monster: Monster) -> Unit, index: String): LiveData<Monster> {
        val retrofit = InjectorUtils.getRetrofitObject()
        val dndApi = InjectorUtils.createDnDApiInterface(retrofit)
        val call = dndApi.getMonsterDetails(index)

        call.enqueue(object : Callback<MonsterDetailsResult> {
            override fun onFailure(call: Call<MonsterDetailsResult>?, t: Throwable?) {
                Log.v("FAILURE: Retrofit", " getMonsterDetails call failed!")
            }

            override fun onResponse(call: Call<MonsterDetailsResult>?, response: Response<MonsterDetailsResult>?) {
                if (!response!!.isSuccessful) {
                    Log.v("FAILURE: Retrofit", " getMonsterDetails call response " + response.code() + "!")
                }
                else {
                    val monsterDetails = response.body()
                    if (monsterDetails != null && monsterDetails.armor_class.isNotEmpty()) {
                        val monsterToCreate = Monster(1, monsterDetails.index, monsterDetails.image, monsterDetails.name, monsterDetails.desc, monsterDetails.size, monsterDetails.type, monsterDetails.subtype,
                            monsterDetails.alignment, monsterDetails.armor_class[0].value, monsterDetails.armor_class[0].type, monsterDetails.hit_points, monsterDetails.hit_points_roll,
                            monsterDetails.speed.toString(), monsterDetails.strength, monsterDetails.dexterity, monsterDetails.constitution, monsterDetails.intelligence, monsterDetails.wisdom,
                            monsterDetails.charisma, monsterDetails.xp, monsterDetails.damage_vulnerabilities, monsterDetails.damage_resistances, monsterDetails.damage_immunities,
                            monsterDetails.languages, monsterDetails.actions.map { a -> a.name })
                        monsterDao.setMonsterDetails(monsterToCreate)
                        toEdit(monsterToCreate)
                    }
                }
            }
        })

        return monsterDao.getMonsterDetails()
    }

    fun getMonsterImage(toEdit: (image: Bitmap) -> Unit, index: String) {
        val retrofit = InjectorUtils.getRetrofitObject()
        val dndApi = InjectorUtils.createDnDApiInterface(retrofit)
        val call = dndApi.getMonsterImage(index)

        call.enqueue(object : Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>?, t: Throwable?) {
                Log.v("FAILURE: Retrofit", " getMonsterImage call failed!")
            }

            override fun onResponse(call: Call<ResponseBody>?, response: Response<ResponseBody>?) {
                if (!response!!.isSuccessful) {
                    Log.v("FAILURE: Retrofit", " getMonsterImage call response " + response.code() + "!")
                }
                else {
                    try {
                        val inputStream = response.body()!!.byteStream()
                        toEdit(BitmapFactory.decodeStream(inputStream))
                    } catch (e: Exception) {
                        Log.v("FAILURE: Retrofit", " getMonsterImage exception " + e.message + "!")
                    }
                }
            }
        })
    }

    fun getMonsters(listToEdit: MutableList<MonsterElement>): LiveData<List<MonsterElement>> {
        val retrofit = InjectorUtils.getRetrofitObject()
        val dndApi = InjectorUtils.createDnDApiInterface(retrofit)
        val call = dndApi.getMonsters()

        call.enqueue(object : Callback<MonstersResult> {
            override fun onFailure(call: Call<MonstersResult>?, t: Throwable?) {
                Log.v("FAILURE: Retrofit", " getMonsters call failed!")
            }

            override fun onResponse(call: Call<MonstersResult>?, response: Response<MonstersResult>?) {
                if (!response!!.isSuccessful) {
                    Log.v("FAILURE: Retrofit", " getMonsters call response " + response.code() + "!")
                }
                else {
                    val monsters = response.body()
                    monsterDao.deleteMonsters()
                    listToEdit.clear()
                    for (m: MonsterElement in monsters!!.results) {
                        monsterDao.addMonster(m)
                        listToEdit.add(m)
                    }
                }
            }
        })

        return monsterDao.getMonsters()
    }

    fun updateMonster(listToEdit: MutableList<MonsterElement>, monster: MonsterElement) {
        //Mocked layer.
        monsterDao.updateMonster(monster)
        listToEdit.removeAll { m -> m.index == monster.index }
        listToEdit.plus(monster)
    }

    fun deleteMonster(listToEdit: MutableList<MonsterElement>, monster: MonsterElement) {
        //Mocked layer.
        monsterDao.deleteMonster(monster.index)
        listToEdit.remove(monster)
    }

    companion object {
        @Volatile private var instance: MonsterRepository? = null

        fun getInstance(monsterDao: MonsterDao) =
            instance ?: synchronized(this) {
                instance ?: MonsterRepository(monsterDao).also { instance = it }
            }
    }
}