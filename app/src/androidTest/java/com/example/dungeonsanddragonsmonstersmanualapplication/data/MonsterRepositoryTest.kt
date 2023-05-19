package com.example.dungeonsanddragonsmonstersmanualapplication.data

import com.example.dungeonsanddragonsmonstersmanualapplication.models.MonsterElement
import com.example.dungeonsanddragonsmonstersmanualapplication.utils.TesterUtils
import org.junit.Test
import org.junit.Assert.*

class MonsterRepositoryTest {

    private var items: MutableList<MonsterElement> = mutableListOf()

    @Test
    fun addMonster() {
        items = mutableListOf()
        TesterUtils.createAddMonster(items, MonsterElement("Test1", "TestUpdated", "Test1"))
        assertTrue(items.size == 1)
    }

    @Test
    fun getMonster() {
        items = mutableListOf(MonsterElement("Test1", "Test1", "Test1"))
        val monster = TesterUtils.createGetMonsterCall(items, "Test1")
        assertTrue(monster != null)
    }

    @Test
    fun getMonsters() {
        items = mutableListOf()
        TesterUtils.createGetMonstersCall(items)
        assertTrue(items.size > 0)
    }

    @Test
    fun updateMonster() {
        items = mutableListOf(MonsterElement("Test1", "Test1", "Test1"))
        val monsterToUpdate = MonsterElement("Test1", "TestUpdated", "Test1")
        TesterUtils.createUpdateMonsterCall(items, monsterToUpdate)
        assertTrue(items[0].name == "TestUpdated")
    }

    @Test
    fun deleteMonster() {
        items = mutableListOf(MonsterElement("Test1", "Test1", "Test1"))
        TesterUtils.createDeleteMonster(items, items[0])
        assertTrue(items.size == 0)
    }
}