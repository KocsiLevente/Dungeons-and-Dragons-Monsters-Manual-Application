package com.example.dungeonsanddragonsmonstersmanualapplication.viewmodels

import androidx.lifecycle.ViewModel
import com.example.dungeonsanddragonsmonstersmanualapplication.data.MonsterRepository
import com.example.dungeonsanddragonsmonstersmanualapplication.models.Monster

class MonsterDetailsViewModel(private val monsterRepository: MonsterRepository) : ViewModel() {

    fun addMonster(monster: Monster) = monsterRepository.addMonster(monster)

    fun getMonster(id: Int) = monsterRepository.getMonster(id)
}
