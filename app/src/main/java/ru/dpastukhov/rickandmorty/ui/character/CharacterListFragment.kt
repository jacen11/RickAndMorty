package ru.dpastukhov.rickandmorty.ui.character

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import kotlinx.android.synthetic.main.fragment_character.*
import ru.dpastukhov.rickandmorty.R


class CharacterListFragment : Fragment() {

    private val viewModel: CharacterListViewModel by viewModels<CharacterListViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_character, container, false)

        viewModel.load()

        viewModel.characterList.observe(viewLifecycleOwner, {
            if (it!=null){
                rvCharacters.adapter = CharacterAdapter(it ?: emptyList())
                Log.d("CharacterFragment", "добавление адаптера")
                // Log.d("CharacterFragment", it?.get(0)?.name)
            }
        })

        return root
    }
}