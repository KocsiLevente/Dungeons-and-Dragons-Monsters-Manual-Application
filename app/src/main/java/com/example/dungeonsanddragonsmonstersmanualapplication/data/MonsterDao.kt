package com.example.dungeonsanddragonsmonstersmanualapplication.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.dungeonsanddragonsmonstersmanualapplication.models.Monster

class MonsterDao {

    private val monsterList = mutableListOf<Monster>()
    private val monsters = MutableLiveData<List<Monster>>()

    init {
        monsters.value = monsterList
    }

    fun addMonster(monster: Monster) {
        monsterList.add(monster)
        monsters.value = monsterList
    }

    fun getMonster(id: Int) = monsterList.single { monster -> monster.id == id }

    fun getMonsters() = monsters as LiveData<List<Monster>>
}