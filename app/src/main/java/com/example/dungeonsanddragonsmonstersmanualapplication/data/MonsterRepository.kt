package com.example.dungeonsanddragonsmonstersmanualapplication.data

import com.example.dungeonsanddragonsmonstersmanualapplication.models.Monster

class MonsterRepository private constructor(private val monsterDao: MonsterDao) {

    fun addMonster(monster: Monster) {
        monsterDao.addMonster(monster)
    }

    fun getMonster(id: Int) {
        monsterDao.getMonster(id)
    }

    fun getMonsters() = monsterDao.getMonsters()

    companion object {
        @Volatile private var instance: MonsterRepository? = null

        fun getInstance(monsterDao: MonsterDao) =
            instance ?: synchronized(this) {
                instance ?: MonsterRepository(monsterDao).also { instance = it }
            }
    }
}