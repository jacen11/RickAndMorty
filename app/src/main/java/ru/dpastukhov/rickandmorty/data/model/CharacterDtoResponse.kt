package ru.dpastukhov.rickandmorty.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class CharacterDtoResponse(
    @Json(name="info")
    val info:InfoPage?,
    val results: List<CharacterDto?>?,
)