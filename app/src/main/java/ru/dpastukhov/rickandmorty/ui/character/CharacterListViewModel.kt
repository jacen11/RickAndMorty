package ru.dpastukhov.rickandmorty.ui.character

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.dpastukhov.rickandmorty.data.model.CharacterDto
import ru.dpastukhov.rickandmorty.data.repo.CharacterRepository
import ru.dpastukhov.rickandmorty.domain.ApiService
import ru.dpastukhov.rickandmorty.ui.BaseViewModel
import javax.inject.Inject

class CharacterListViewModel : BaseViewModel() {
    @Inject
    lateinit var apiService: ApiService

    @Inject
    lateinit var characterRepository: CharacterRepository

    val characterList: MutableLiveData<List<CharacterDto?>?> = MutableLiveData()

    fun load(name:String?=null) {

        viewModelScope.launch {
            characterList.value = characterRepository.getCharacter(name).results
        }
    }

}