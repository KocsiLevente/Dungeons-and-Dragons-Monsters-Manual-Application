package com.example.dungeonsanddragonsmonstersmanualapplication.data

import com.example.dungeonsanddragonsmonstersmanualapplication.models.MonsterElement
import com.example.dungeonsanddragonsmonstersmanualapplication.utils.TesterUtils
import org.junit.Test
import org.junit.Assert.*

class DnDApplicationTest {

    private var items: MutableList<MonsterElement> = mutableListOf()

    @Test
    fun testAddMonster() {
        items = mutableListOf()
        TesterUtils.testAddMonster(items, MonsterElement("Test1", "TestUpdated", "Test1"))
        assertTrue(items.size == 1)
    }

    @Test
    fun testGetMonster() {
        items = mutableListOf(MonsterElement("Test1", "Test1", "Test1"))
        val monster = TesterUtils.testGetMonster(items, "Test1")
        assertTrue(monster != null)
    }

    @Test
    fun testGetMonsters() {
        items = mutableListOf()
        TesterUtils.testGetMonsters(items)
        assertTrue(items.size > 0)
    }

    @Test
    fun testUpdateMonster() {
        items = mutableListOf(MonsterElement("Test1", "Test1", "Test1"))
        val monsterToUpdate = MonsterElement("Test1", "TestUpdated", "Test1")
        TesterUtils.testUpdateMonster(items, monsterToUpdate)
        assertTrue(items[0].name == "TestUpdated")
    }

    @Test
    fun testDeleteMonster() {
        items = mutableListOf(MonsterElement("Test1", "Test1", "Test1"))
        TesterUtils.testDeleteMonster(items, items[0])
        assertTrue(items.size == 0)
    }

    @Test
    fun testGetMonsterDetailsCall() {
        TesterUtils.testGetMonsterDetailsCall(this::assertSuccess, this::assertFailed)
    }

    @Test
    fun testGetMonsterImageCall() {
        TesterUtils.testGetMonsterImageCall(this::assertSuccess, this::assertFailed)
    }

    @Test
    fun testGetMonstersCall() {
        items = mutableListOf()
        TesterUtils.testGetMonstersCall(this::assertSuccess, this::assertFailed)
    }

    //Needed for callback functions.
    private fun assertSuccess() {
        assertTrue(true)
    }

    //Needed for callback functions.
    private fun assertFailed() {
        assertTrue(false)
    }
}