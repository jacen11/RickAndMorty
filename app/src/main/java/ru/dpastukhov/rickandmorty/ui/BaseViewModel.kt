package ru.dpastukhov.rickandmorty.ui

import androidx.lifecycle.ViewModel
import ru.dpastukhov.rickandmorty.di.AppComponent
import ru.dpastukhov.rickandmorty.di.DaggerAppComponent
import ru.dpastukhov.rickandmorty.di.RestModule
import ru.dpastukhov.rickandmorty.ui.character.CharacterViewModel

abstract class BaseViewModel : ViewModel() {

    private val injector: AppComponent = DaggerAppComponent
        .builder()
        .restModule(RestModule)
        .build()

    init {
        inject()
    }

    private fun inject() {
        when (this) {
            is CharacterViewModel -> injector.inject(this)
        }
    }
}