package ru.dpastukhov.rickandmorty.di

import dagger.Component
import dagger.Module
import ru.dpastukhov.rickandmorty.ui.character.CharacterListViewModel
import javax.inject.Singleton

@Component(
    modules = [
        RestModule::class
    ]
)
@Singleton
interface AppComponent {
    fun inject(characterViewModel: CharacterListViewModel)

    @Component.Builder
    interface Builder{
        fun build(): AppComponent

        fun restModule(restModule: RestModule):Builder
    }
}

@Module
class DomainModule{

}