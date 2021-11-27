package ru.dpastukhov.rickandmorty.domain

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import ru.dpastukhov.rickandmorty.data.model.CharacterDtoResponse
import ru.dpastukhov.rickandmorty.data.model.RsCharacters

interface ApiService {

    @GET("character/")
    fun getAllCharacter(): Observable<RsCharacters>

    @GET("character/")
    suspend fun getCharacter(
        @Query("name") name: String?=null
    ): CharacterDtoResponse
}