package ru.dpastukhov.rickandmorty.data.repo

import ru.dpastukhov.rickandmorty.domain.ApiService
import javax.inject.Inject

class CharacterRepository @Inject constructor(private val api: ApiService) {

    suspend fun getCharacter(page: Int, name: String? = null) = api.getCharacter(page, name)

}