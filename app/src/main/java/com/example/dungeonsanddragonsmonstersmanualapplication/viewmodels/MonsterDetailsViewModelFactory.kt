package com.example.dungeonsanddragonsmonstersmanualapplication.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.dungeonsanddragonsmonstersmanualapplication.data.MonsterRepository

class MonsterDetailsViewModelFactory(private val monsterRepository: MonsterRepository) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MonsterDetailsViewModel(monsterRepository) as T
    }
}