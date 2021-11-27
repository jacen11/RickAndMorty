package ru.dpastukhov.rickandmorty.ui.character

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.dpastukhov.rickandmorty.R
import ru.dpastukhov.rickandmorty.data.model.CharacterDto

class CharDiffUtil : DiffUtil.ItemCallback<CharacterDto>() {
    override fun areItemsTheSame(oldItem: CharacterDto, newItem: CharacterDto): Boolean = oldItem == newItem //todo
    override fun areContentsTheSame(oldItem: CharacterDto, newItem: CharacterDto): Boolean = oldItem == newItem //todo
}

class CharacterAdapter : PagingDataAdapter<CharacterDto, CharacterAdapter.CharacterHolder>(CharDiffUtil()) {

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

    override fun onBindViewHolder(holder: CharacterHolder, position: Int) {
        val item = getItem(position) as CharacterDto
        holder.name.text = item.name
        holder.status.text = item.status
        holder.origin.text = item.origin?.name
        Glide.with(holder.image).load(item.image).into(holder.image);
    }
}