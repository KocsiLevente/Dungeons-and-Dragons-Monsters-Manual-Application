package com.example.dungeonsanddragonsmonstersmanualapplication.fragments

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.dungeonsanddragonsmonstersmanualapplication.R
import com.example.dungeonsanddragonsmonstersmanualapplication.models.Monster
import com.example.dungeonsanddragonsmonstersmanualapplication.utils.InjectorUtils
import com.example.dungeonsanddragonsmonstersmanualapplication.viewmodels.MonsterDetailsViewModel


class MonsterDetailsFragment : Fragment() {

    companion object {
        fun newInstance() =
            MonsterDetailsFragment()
    }

    private lateinit var viewModel: MonsterDetailsViewModel
    private lateinit var monster: Monster
    private lateinit var image: Bitmap

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.monster_details_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = InjectorUtils.provideMonsterDetailsViewModelFactory().create(MonsterDetailsViewModel::class.java)

        viewModel.index = activity!!.intent.getStringExtra("index")
        viewModel.getMonsterImage(this::refreshMonsterImage, viewModel.index!!)
        viewModel.getMonsterDetails(this::refreshMonsterDetails, viewModel.index!!)
    }

    private fun refreshMonsterDetails(monsterData: Monster) {
        monster = monsterData

        val textViewName = view!!.findViewById<TextView>(R.id.monster_title)
        textViewName.text = monster.name
        val textViewDesc = view!!.findViewById<TextView>(R.id.monster_desc)
        textViewDesc.text = monster.desc
    }

    private fun refreshMonsterImage(data: Bitmap) {
        image = data

        val imageView = view!!.findViewById<ImageView>(R.id.monster_image)
        val d: Drawable = BitmapDrawable(resources, image)
        imageView.setImageDrawable(d)
    }
}
