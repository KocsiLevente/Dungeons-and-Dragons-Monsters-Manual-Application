package com.example.dungeonsanddragonsmonstersmanualapplication.viewmodels

import androidx.lifecycle.ViewModel
import com.example.dungeonsanddragonsmonstersmanualapplication.data.MonsterRepository

class MonsterListViewModel(private val monsterRepository: MonsterRepository) : ViewModel() {

    fun getMonsters() = monsterRepository.getMonsters()
}
