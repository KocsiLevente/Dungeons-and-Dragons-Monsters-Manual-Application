package com.example.dungeonsanddragonsmonstersmanualapplication.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.dungeonsanddragonsmonstersmanualapplication.data.MonsterRepository
import com.example.dungeonsanddragonsmonstersmanualapplication.models.MonsterElement

class MonsterListViewModel(private val monsterRepository: MonsterRepository) : ViewModel() {

    fun getMonsters(listToEdit: MutableList<MonsterElement>): LiveData<List<MonsterElement>> {
        return monsterRepository.getMonsters(listToEdit)
    }

    fun deleteMonster(listToEdit: MutableList<MonsterElement>, toDelete: MonsterElement) {
        return monsterRepository.deleteMonster(listToEdit, toDelete)
    }
}
