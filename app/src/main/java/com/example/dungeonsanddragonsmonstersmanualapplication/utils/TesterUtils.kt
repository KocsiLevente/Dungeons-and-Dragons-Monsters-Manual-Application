package com.example.dungeonsanddragonsmonstersmanualapplication.utils

import android.util.Log
import com.example.dungeonsanddragonsmonstersmanualapplication.models.Monster
import com.example.dungeonsanddragonsmonstersmanualapplication.models.MonsterDetailsResult
import com.example.dungeonsanddragonsmonstersmanualapplication.models.MonsterElement
import com.example.dungeonsanddragonsmonstersmanualapplication.models.MonstersResult
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object TesterUtils {
    //Only for testing.
    fun createGetMonsterDetailsCall(index: String) {
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
                    if (monsterDetails != null) {
                        val monsterToCreate = Monster(1, monsterDetails.index, monsterDetails.image, monsterDetails.name, monsterDetails.desc, monsterDetails.size, monsterDetails.type, monsterDetails.subtype,
                            monsterDetails.alignment, monsterDetails.armor_class[0].value, monsterDetails.armor_class[0].type, monsterDetails.hit_points, monsterDetails.hit_points_roll,
                            monsterDetails.speed.toString(), monsterDetails.strength, monsterDetails.dexterity, monsterDetails.constitution, monsterDetails.intelligence, monsterDetails.wisdom,
                            monsterDetails.charisma, monsterDetails.xp, monsterDetails.damage_vulnerabilities, monsterDetails.damage_resistances, monsterDetails.damage_immunities,
                            monsterDetails.languages, monsterDetails.actions.map { a -> a.name + ": " + a.desc })
                        Log.v("MONSTER DETAILS", " $monsterToCreate")
                    }
                }
            }
        })
    }

    //Only for testing.
    fun createGetMonstersCall() {
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
                    for (m: MonsterElement in monsters!!.results) {
                        Log.v("MONSTER", " index: " + m.index)
                    }
                }
            }
        })
    }
}