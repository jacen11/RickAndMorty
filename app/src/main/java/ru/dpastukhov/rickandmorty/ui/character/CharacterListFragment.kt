package ru.dpastukhov.rickandmorty.ui.character

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
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

        // viewModel.load()



//        viewModel.characterList.observe(viewLifecycleOwner, {
//            if (it != null) {
//                binding.rvCharacters.adapter = CharacterAdapter(it)
//            }
//        })

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val characterAdapter = CharacterAdapter()

        binding.inputSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit
            override fun afterTextChanged(s: Editable?) = Unit
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.search = s?.toString()
                characterAdapter.refresh()
            // viewModel.load(s.toString())
            }
        })



        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            characterAdapter.loadStateFlow.collectLatest { loadStates ->
                binding.swipeRefresh.isRefreshing = loadStates.refresh is LoadState.Loading
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.characterList.collectLatest {
                characterAdapter.submitData(it)
            }
        }

        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            characterAdapter.loadStateFlow
                .distinctUntilChangedBy { it.refresh }
                .filter { it.refresh is LoadState.NotLoading }
                .collect { binding.rvCharacters.scrollToPosition(0) }
        }

        binding.swipeRefresh.setOnRefreshListener {
            characterAdapter.refresh()
        }


        binding.rvCharacters.adapter = characterAdapter
    }
}