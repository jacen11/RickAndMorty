package ru.dpastukhov.rickandmorty.domain

import retrofit2.http.GET
import retrofit2.http.Query
import ru.dpastukhov.rickandmorty.data.model.CharacterDtoResponse

interface ApiService {

    @GET("character/")
    suspend fun getCharacter(
        @Query("page") page: Int? = null,
        @Query("name") name: String? = null,
    ): CharacterDtoResponse
}