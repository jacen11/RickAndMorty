package ru.dpastukhov.rickandmorty.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Origin(
    @SerializedName("name")
    @Expose
    var name: String,

    @SerializedName("url")
    @Expose
    var url: String
)

