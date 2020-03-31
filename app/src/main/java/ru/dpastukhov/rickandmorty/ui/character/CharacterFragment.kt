package ru.dpastukhov.rickandmorty.ui.character

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_character.*
import ru.dpastukhov.rickandmorty.R
import ru.dpastukhov.rickandmorty.adapter.CharacterAdapter


class CharacterFragment : Fragment() {

    private lateinit var characterViewModel: CharacterViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        characterViewModel =
            ViewModelProviders.of(this).get(CharacterViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_character, container, false)

//        val llm = LinearLayoutManager(context)
//        llm.orientation = LinearLayoutManager.VERTICAL
//        rvCharacters.setLayoutManager(llm)
        //rvCharacters.setAdapter(adapter)


        characterViewModel.adapter.observe(this, Observer {

            rvCharacters.layoutManager = LinearLayoutManager(context)
            rvCharacters.adapter = CharacterAdapter(it ?: emptyList())
            Log.d("CharacterFragment", "добавление адаптера")
            Log.d("CharacterFragment", it?.get(0)?.name)
        })

        characterViewModel.text.observe(this, Observer {
            txt2.text = it
        })
        return root
    }
}