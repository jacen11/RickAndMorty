package ru.dpastukhov.rickandmorty.ui.character

import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.Flow
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

    var search: String? = null

    private val pagingConfig = PagingConfig(initialLoadSize = 10, pageSize = 6)

    val characterList: Flow<PagingData<CharacterDto>> = Pager(pagingConfig) { CharacterPagingSource(characterRepository, search) }
            .flow
            .cachedIn(viewModelScope)


}