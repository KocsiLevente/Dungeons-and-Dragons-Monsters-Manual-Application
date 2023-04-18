package com.example.dungeonsanddragonsmonstersmanualapplication.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dungeonsanddragonsmonstersmanualapplication.R
import com.example.dungeonsanddragonsmonstersmanualapplication.utils.InjectorUtils
import com.example.dungeonsanddragonsmonstersmanualapplication.viewmodels.MonsterDetailsViewModel

class MonsterDetailsFragment : Fragment() {

    companion object {
        fun newInstance() =
            MonsterDetailsFragment()
    }

    private lateinit var viewModel: MonsterDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.monster_details_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = InjectorUtils.provideMonsterDetailsViewModelFactory().create(MonsterDetailsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
