package com.example.dungeonsanddragonsmonstersmanualapplication.utils

import com.example.dungeonsanddragonsmonstersmanualapplication.models.MonsterElement

object TesterUtils {

    //Only for testing.
    fun createAddMonster(listToEdit: MutableList<MonsterElement>, monster: MonsterElement) {
        listToEdit.add(monster)
    }

    //Only for testing.
    fun createGetMonsterCall(listToEdit: MutableList<MonsterElement>, index: String): MonsterElement? {
        return listToEdit.find { m -> m.index == index }
    }

    //Only for testing.
    fun createGetMonstersCall(listToEdit: MutableList<MonsterElement>) {
        listToEdit.add(MonsterElement("Test1", "Test1", "Test1"))
    }

    //Only for testing.
    fun createUpdateMonsterCall(listToEdit: MutableList<MonsterElement>, monster: MonsterElement) {
        listToEdit.removeAll { m -> m.index == monster.index }
        listToEdit.add(monster)
    }

    //Only for testing.
    fun createDeleteMonster(listToEdit: MutableList<MonsterElement>, monster: MonsterElement) {
        listToEdit.remove(monster)
    }
}