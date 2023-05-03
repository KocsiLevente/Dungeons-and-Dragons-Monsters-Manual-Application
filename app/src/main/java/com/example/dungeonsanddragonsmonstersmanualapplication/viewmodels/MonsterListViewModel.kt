package com.example.dungeonsanddragonsmonstersmanualapplication.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.dungeonsanddragonsmonstersmanualapplication.data.MonsterRepository
import com.example.dungeonsanddragonsmonstersmanualapplication.models.MonsterElement

class MonsterListViewModel(private val monsterRepository: MonsterRepository) : ViewModel() {

    val monsters: LiveData<List<MonsterElement>>
        get() = monsterRepository.getMonsters()
}
