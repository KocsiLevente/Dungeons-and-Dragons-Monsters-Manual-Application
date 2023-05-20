package com.example.dungeonsanddragonsmonstersmanualapplication.viewmodels

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import com.example.dungeonsanddragonsmonstersmanualapplication.data.MonsterRepository
import com.example.dungeonsanddragonsmonstersmanualapplication.models.Monster

class MonsterDetailsViewModel(private val monsterRepository: MonsterRepository) : ViewModel() {

    var index: String? = ""
        get() = field
        set(value) {
            field = value
        }

    fun getMonsterDetails(toEdit: (monster: Monster) -> Unit, index: String) = monsterRepository.getMonsterDetails(toEdit, index)

    fun getMonsterImage(toEdit: (image: Bitmap) -> Unit, index: String) {
        monsterRepository.getMonsterImage(toEdit, index)
    }
}
