package com.example.dungeonsanddragonsmonstersmanualapplication.utils

import com.example.dungeonsanddragonsmonstersmanualapplication.data.MonsterDatabase
import com.example.dungeonsanddragonsmonstersmanualapplication.data.MonsterRepository
import com.example.dungeonsanddragonsmonstersmanualapplication.viewmodels.MonsterDetailsViewModelFactory
import com.example.dungeonsanddragonsmonstersmanualapplication.viewmodels.MonsterListViewModelFactory

object InjectorUtils {

    fun provideMonsterListViewModelFactory(): MonsterListViewModelFactory {
        val monsterRepository = MonsterRepository.getInstance(MonsterDatabase.getInstance().monsterDao)
        return MonsterListViewModelFactory(monsterRepository)
    }

    fun provideMonsterDetailsViewModelFactory(): MonsterDetailsViewModelFactory {
        val monsterRepository = MonsterRepository.getInstance(MonsterDatabase.getInstance().monsterDao)
        return MonsterDetailsViewModelFactory(monsterRepository)
    }
}