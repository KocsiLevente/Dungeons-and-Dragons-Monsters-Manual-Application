package com.example.dungeonsanddragonsmonstersmanualapplication.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dungeonsanddragonsmonstersmanualapplication.R
import com.example.dungeonsanddragonsmonstersmanualapplication.utils.InjectorUtils
import com.example.dungeonsanddragonsmonstersmanualapplication.utils.TesterUtils
import com.example.dungeonsanddragonsmonstersmanualapplication.viewmodels.MonsterListViewModel

class MonsterListFragment : Fragment() {

    companion object {
        fun newInstance() =
            MonsterListFragment()
    }

    private lateinit var viewModel: MonsterListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.monster_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = InjectorUtils.provideMonsterListViewModelFactory().create(MonsterListViewModel::class.java)
        // TODO: Use the ViewModel
        // TODO: Remove TesterUtils
        TesterUtils.createGetMonstersCall()
        TesterUtils.createGetMonsterDetailsCall("bandit")
    }
}
