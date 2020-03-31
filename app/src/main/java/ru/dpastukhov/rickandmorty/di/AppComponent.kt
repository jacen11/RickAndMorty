package ru.dpastukhov.rickandmorty.di

import dagger.Component
import ru.dpastukhov.rickandmorty.MainActivity
import ru.dpastukhov.rickandmorty.data.model.Character
import ru.dpastukhov.rickandmorty.ui.character.CharacterViewModel
import javax.inject.Singleton

@Component(
    modules = [
        RestModule::class
    ]
)
@Singleton
interface AppComponent {
    fun inject(characterViewModel: CharacterViewModel)

    @Component.Builder
    interface Builder{
        fun build(): AppComponent

        fun restModule(restModule: RestModule):Builder
    }
}