package ru.dpastukhov.rickandmorty.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.dpastukhov.rickandmorty.R
import ru.dpastukhov.rickandmorty.data.model.Character

class CharacterAdapter(private val characters: List<Character?>) : RecyclerView.Adapter<CharacterAdapter.CharacterHolder>() {

    class CharacterHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name: TextView = itemView.findViewById(R.id.txtName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterHolder {
        return CharacterHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.character_item_fragment,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = characters.size

    override fun onBindViewHolder(holder: CharacterHolder, position: Int) {
        holder.name.text = characters[position]?.name
    }
}