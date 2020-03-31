package ru.dpastukhov.rickandmorty.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Location(
    @SerializedName("name")
    @Expose
    private val name: String? = null,

    @SerializedName("url")
    @Expose
    private val url: String? = null
)