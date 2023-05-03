package com.example.dungeonsanddragonsmonstersmanualapplication.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.dungeonsanddragonsmonstersmanualapplication.data.MonsterRepository
import com.example.dungeonsanddragonsmonstersmanualapplication.models.Monster
import com.example.dungeonsanddragonsmonstersmanualapplication.models.MonsterElement

class MonsterDetailsViewModel(private val monsterRepository: MonsterRepository) : ViewModel() {

    var index: String = ""
        get() = field
        set(value) {
            field = value
        }

    val monster: LiveData<Monster>?
        get() {
            return if (index.isNotEmpty()) {
                monsterRepository.getMonsterDetails(index)
            } else {
                null
            }
        }

    fun addMonster(monster: MonsterElement) = monsterRepository.addMonster(monster)

    fun getMonster(index: String) = monsterRepository.getMonster(index)
}
