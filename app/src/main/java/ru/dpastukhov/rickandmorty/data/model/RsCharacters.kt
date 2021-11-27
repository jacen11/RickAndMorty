package ru.dpastukhov.rickandmorty.data.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




class RsCharacters {

    @SerializedName("info")
    @Expose
     var info: Info? = null

    @SerializedName("results")
    @Expose
     var results: List<CharacterDto?>? = null


}