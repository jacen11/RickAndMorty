package ru.dpastukhov.rickandmorty.data.repo

import ru.dpastukhov.rickandmorty.domain.ApiService
import javax.inject.Inject

class CharacterRepository @Inject constructor(val api: ApiService) {

    suspend fun getCharacter(name: String?=null) =  api.getCharacter(name)

}