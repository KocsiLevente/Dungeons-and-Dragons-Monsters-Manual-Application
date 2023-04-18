package com.example.dungeonsanddragonsmonstersmanualapplication.data

class MonsterDatabase private constructor() {

    var monsterDao = MonsterDao()
        private set

    companion object {
        @Volatile private var instance: MonsterDatabase? = null

        fun getInstance() =
            instance ?: synchronized(this) {
                instance ?: MonsterDatabase().also { instance = it }
            }
    }
}