package ru.dpastukhov.rickandmorty.ui.character

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.fragment_character.*
import ru.dpastukhov.rickandmorty.R

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
        characterViewModel.text.observe(this, Observer {
            txt2.text = it
        })
        return root
    }
}