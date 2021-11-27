package ru.dpastukhov.rickandmorty.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Character(
    var id: Int? = null,
    var name: String? = null,
    var status: String? = null,
    var species: String? = null,
    var type: String? = null,
    var gender: String? = null,
    var origin: Origin? = null,
    var location: Location? = null,
    var image: String? = null,
    var episode: List<String?>? = null,
    var url: String? = null,
    var created: String? = null
)

