package ru.dpastukhov.rickandmorty.data.model

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class CharacterDeserializer : JsonDeserializer<CharacterDto> {
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): CharacterDto {
        var test = ""
        return CharacterDto()
    }

}
