package com.example.dungeonsanddragonsmonstersmanualapplication.data

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.dungeonsanddragonsmonstersmanualapplication.models.Monster
import com.example.dungeonsanddragonsmonstersmanualapplication.models.MonsterDetailsResult
import com.example.dungeonsanddragonsmonstersmanualapplication.models.MonsterElement
import com.example.dungeonsanddragonsmonstersmanualapplication.models.MonstersResult
import com.example.dungeonsanddragonsmonstersmanualapplication.utils.InjectorUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MonsterRepository private constructor(private val monsterDao: MonsterDao) {

    fun addMonster(monster: MonsterElement) {
        monsterDao.addMonster(monster)
    }

    fun getMonster(index: String): MonsterElement {
        return monsterDao.getMonster(index)
    }

    fun getMonsterDetails(index: String): LiveData<Monster> {
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
                            monsterDetails.languages, monsterDetails.actions.map { a -> a.name + ": " + a.desc })
                        monsterDao.setMonsterDetails(monsterToCreate)
                    }
                }
            }
        })

        return monsterDao.getMonsterDetails()
    }

    fun setMonsterDetails(monster: Monster) {
        monsterDao.setMonsterDetails(monster)
    }

    fun getMonsters(listToEdit: MutableList<String>): LiveData<List<MonsterElement>> {
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
                        listToEdit.add(m.toString())
                    }
                }
            }
        })

        return monsterDao.getMonsters()
    }

    fun updateMonster(monsterToUpdate: MonsterElement) {
        monsterDao.updateMonster(monsterToUpdate)
    }

    fun deleteMonster(index: String) {
        monsterDao.deleteMonster(index)
    }

    fun deleteMonsters() {
        monsterDao.deleteMonsters()
    }

    companion object {
        @Volatile private var instance: MonsterRepository? = null

        fun getInstance(monsterDao: MonsterDao) =
            instance ?: synchronized(this) {
                instance ?: MonsterRepository(monsterDao).also { instance = it }
            }
    }
}