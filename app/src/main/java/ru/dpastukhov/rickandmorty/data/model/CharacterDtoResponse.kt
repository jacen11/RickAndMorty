package ru.dpastukhov.rickandmorty.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class CharacterDtoResponse(
    val results: List<CharacterDto>
)