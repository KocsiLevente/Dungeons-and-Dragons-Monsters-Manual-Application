package com.example.dungeonsanddragonsmonstersmanualapplication.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.dungeonsanddragonsmonstersmanualapplication.R
import com.example.dungeonsanddragonsmonstersmanualapplication.fragments.MonsterDetailsFragment

class MonsterDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.monster_details_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MonsterDetailsFragment.newInstance())
                .commitNow()
        }
    }
}
