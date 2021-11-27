package ru.dpastukhov.rickandmorty.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class InfoPage(
    var count: Int? = null,
    var pages: Int? = null,
    var next: String? = null,
    var prev: String? = null,
)