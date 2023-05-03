package com.example.dungeonsanddragonsmonstersmanualapplication.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.dungeonsanddragonsmonstersmanualapplication.models.Monster
import com.example.dungeonsanddragonsmonstersmanualapplication.models.MonsterElement

class MonsterDao {

    private val monsterList = mutableListOf<MonsterElement>()
    private val monsters = MutableLiveData<List<MonsterElement>>()
    private val monsterDetails = MutableLiveData<Monster>()

    init {
        monsters.value = monsterList
    }

    fun addMonster(monster: MonsterElement) {
        monsterList.add(monster)
        monsters.value = monsterList
    }

    fun getMonster(index: String) = monsterList.single { monster -> monster.index == index }

    fun getMonsterDetails() = monsterDetails

    fun setMonsterDetails(monster: Monster) {
        monsterDetails.value = monster
    }

    fun getMonsters() = monsters as LiveData<List<MonsterElement>>

    fun updateMonster(monsterToUpdate: MonsterElement) {
        monsterList.removeAll { monster -> monster.index == monsterToUpdate.index }
        monsterList.add(monsterToUpdate)
        monsters.value = monsterList
    }

    fun deleteMonster(index: String) {
        monsterList.removeAll { monster -> monster.index == index }
        monsters.value = monsterList
    }

    fun deleteMonsters() {
        monsterList.clear()
        monsters.value = monsterList
    }
}