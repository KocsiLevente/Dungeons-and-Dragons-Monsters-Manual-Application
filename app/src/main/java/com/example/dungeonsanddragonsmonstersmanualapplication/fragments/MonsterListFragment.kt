package com.example.dungeonsanddragonsmonstersmanualapplication.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import com.example.dungeonsanddragonsmonstersmanualapplication.R
import com.example.dungeonsanddragonsmonstersmanualapplication.activities.MonsterDetailsActivity
import com.example.dungeonsanddragonsmonstersmanualapplication.utils.InjectorUtils
import com.example.dungeonsanddragonsmonstersmanualapplication.viewmodels.MonsterListViewModel
import kotlinx.android.synthetic.main.monster_list_fragment.*

class MonsterListFragment : Fragment() {

    companion object {
        fun newInstance() =
            MonsterListFragment()
    }

    private lateinit var viewModel: MonsterListViewModel
    private var items: MutableList<String> = mutableListOf()

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
        val adapter = ArrayAdapter<String>(context!!, android.R.layout.simple_list_item_1, items)

        //Setting up listeners.
        button_add.setOnClickListener {
            Toast.makeText(context!!, "Only available in final version!", Toast.LENGTH_SHORT).show()
        }
        listView.onItemClickListener = AdapterView.OnItemClickListener { _, _, _, _ ->
            val intent = Intent(context, MonsterDetailsActivity::class.java)
            startActivity(intent)
        }

        adapter.notifyDataSetChanged()
        viewModel.getMonsters(items)
        listView.adapter = adapter
    }
}
