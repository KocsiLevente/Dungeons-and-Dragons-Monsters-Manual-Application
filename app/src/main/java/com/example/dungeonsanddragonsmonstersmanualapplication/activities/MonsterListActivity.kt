package com.example.dungeonsanddragonsmonstersmanualapplication.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.dungeonsanddragonsmonstersmanualapplication.R
import com.example.dungeonsanddragonsmonstersmanualapplication.fragments.MonsterListFragment

class MonsterListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.monster_list_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MonsterListFragment.newInstance())
                .commitNow()
        }
    }
}
