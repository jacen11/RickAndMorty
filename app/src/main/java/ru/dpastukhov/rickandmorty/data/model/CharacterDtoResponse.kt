package ru.dpastukhov.rickandmorty.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class CharacterDtoResponse(
    val info: InfoPage?,
    val results: List<CharacterDto?>?,
    val error: String?
)