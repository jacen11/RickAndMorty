package ru.dpastukhov.rickandmorty.ui.character

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.dpastukhov.rickandmorty.R
import ru.dpastukhov.rickandmorty.data.model.CharacterDto

class CharacterAdapter(private val characters: List<CharacterDto?>) : RecyclerView.Adapter<CharacterAdapter.CharacterHolder>() {

    class CharacterHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name: TextView = itemView.findViewById(R.id.txtName)
        var status: TextView = itemView.findViewById(R.id.txtStatus)
        var origin: TextView = itemView.findViewById(R.id.txtOrigin)
        var image: ImageView = itemView.findViewById(R.id.img)
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
        holder.status.text = characters[position]?.status
        holder.origin.text = characters[position]?.origin?.name
        Glide.with(holder.image).load(characters[position]?.image).into(holder.image);
    }
}