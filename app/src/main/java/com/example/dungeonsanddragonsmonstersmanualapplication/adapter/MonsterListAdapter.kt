package com.example.dungeonsanddragonsmonstersmanualapplication.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.dungeonsanddragonsmonstersmanualapplication.R
import com.example.dungeonsanddragonsmonstersmanualapplication.activities.MonsterDetailsActivity
import com.example.dungeonsanddragonsmonstersmanualapplication.models.MonsterElement

class MonsterListAdapter(context: Context, resource: Int, private val monsters: MutableList<MonsterElement>, private val deleteFunc: (listToEdit: MutableList<MonsterElement>, toDelete: MonsterElement) -> Unit) :
    ArrayAdapter<MonsterElement>(context, resource, monsters) {

    override fun getCount(): Int {
        return monsters.size
    }

    override fun getItem(position: Int): MonsterElement {
        return monsters[position]
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = LayoutInflater.from(context).inflate(R.layout.monster_list_item, parent, false)

        val buttonDelete = view!!.findViewById<Button>(R.id.button_delete)
        val textViewName = view.findViewById<TextView>(R.id.monster_name)

        //Setting up listeners.
        buttonDelete.setOnClickListener {
            deleteFunc(monsters, monsters[position])
            notifyDataSetChanged()
            Toast.makeText(context, "Monster deleted locally!", Toast.LENGTH_SHORT).show()
        }
        textViewName.setOnClickListener {
            val intent = Intent(context, MonsterDetailsActivity::class.java)
            intent.putExtra("index", monsters[position].index)
            context.startActivity(intent)
        }
        textViewName.text = monsters[position].name

        return view
    }
}