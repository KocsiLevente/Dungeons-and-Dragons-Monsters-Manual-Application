package com.example.dungeonsanddragonsmonstersmanualapplication.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.dungeonsanddragonsmonstersmanualapplication.R
import com.example.dungeonsanddragonsmonstersmanualapplication.adapter.MonsterListAdapter
import com.example.dungeonsanddragonsmonstersmanualapplication.models.MonsterElement
import com.example.dungeonsanddragonsmonstersmanualapplication.utils.InjectorUtils
import com.example.dungeonsanddragonsmonstersmanualapplication.viewmodels.MonsterListViewModel
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.monster_list_fragment.*

class MonsterListFragment : Fragment() {

    private lateinit var firebaseAnalytics: FirebaseAnalytics

    companion object {
        fun newInstance() =
            MonsterListFragment()
    }

    private lateinit var viewModel: MonsterListViewModel
    private var items: MutableList<MonsterElement> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.monster_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = InjectorUtils.provideMonsterListViewModelFactory().create(MonsterListViewModel::class.java)

        val listView = view!!.findViewById<ListView>(R.id.list_monsters)
        val adapter = MonsterListAdapter(context!!, R.layout.monster_list_item, items, this::deleteMonster)

        //Setting up listeners.
        button_add.setOnClickListener {
            Toast.makeText(context!!, "Only available in final version!", Toast.LENGTH_SHORT).show()
            throw RuntimeException("Test Crash") // Force a crash for Android Crashlytics.
        }

        adapter.notifyDataSetChanged()
        viewModel.getMonsters(items)
        listView.adapter = adapter

        //FIREBASE
        firebaseAnalytics = Firebase.analytics

        val bundle = Bundle()
        bundle.putString(FirebaseAnalytics.Param.SEARCH_TERM, "MonsterList")
        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SEARCH, bundle)
    }

    private fun deleteMonster(listToEdit: MutableList<MonsterElement>, toDelete: MonsterElement) {
        items.remove(toDelete)
        viewModel.deleteMonster(listToEdit, toDelete)
    }
}
