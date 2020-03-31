package ru.dpastukhov.rickandmorty.data

import io.reactivex.Observable
import retrofit2.http.GET
import ru.dpastukhov.rickandmorty.data.model.RsCharacters

interface ApiService {

@GET("character/")
    fun getAllCharacter(): Observable<RsCharacters>
}