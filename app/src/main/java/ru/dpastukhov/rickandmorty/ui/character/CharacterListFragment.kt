package ru.dpastukhov.rickandmorty.ui.character

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import ru.dpastukhov.rickandmorty.databinding.FragmentCharacterListBinding


class CharacterListFragment : Fragment() {

    private val viewModel: CharacterListViewModel by viewModels()

    private lateinit var binding: FragmentCharacterListBinding

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        binding = FragmentCharacterListBinding.inflate(inflater, container, false)

        viewModel.load()

        binding.inputSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.load(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })

        viewModel.characterList.observe(viewLifecycleOwner, {
            if (it != null) {
                binding.rvCharacters.adapter = CharacterAdapter(it ?: emptyList())
                Log.d("CharacterFragment", "добавление адаптера")
                // Log.d("CharacterFragment", it?.get(0)?.name)
            }
        })

        return binding.root
    }
}