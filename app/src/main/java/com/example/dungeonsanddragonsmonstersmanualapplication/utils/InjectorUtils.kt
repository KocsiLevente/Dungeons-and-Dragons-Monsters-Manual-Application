package com.example.dungeonsanddragonsmonstersmanualapplication.utils

import com.example.dungeonsanddragonsmonstersmanualapplication.data.MonsterDatabase
import com.example.dungeonsanddragonsmonstersmanualapplication.data.MonsterRepository
import com.example.dungeonsanddragonsmonstersmanualapplication.remote.DnDApi
import com.example.dungeonsanddragonsmonstersmanualapplication.viewmodels.MonsterDetailsViewModelFactory
import com.example.dungeonsanddragonsmonstersmanualapplication.viewmodels.MonsterListViewModelFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object InjectorUtils {

    private const val baseUrl: String = "https://www.dnd5eapi.co/api/"

    fun provideMonsterListViewModelFactory(): MonsterListViewModelFactory {
        val monsterRepository = MonsterRepository.getInstance(MonsterDatabase.getInstance().monsterDao)
        return MonsterListViewModelFactory(monsterRepository)
    }

    fun provideMonsterDetailsViewModelFactory(): MonsterDetailsViewModelFactory {
        val monsterRepository = MonsterRepository.getInstance(MonsterDatabase.getInstance().monsterDao)
        return MonsterDetailsViewModelFactory(monsterRepository)
    }

    fun getRetrofitObject(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun createDnDApiInterface(retrofit: Retrofit): DnDApi {
        return retrofit.create(DnDApi::class.java)
    }
}