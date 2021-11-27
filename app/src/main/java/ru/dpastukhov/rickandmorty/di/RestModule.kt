package ru.dpastukhov.rickandmorty.di

import com.google.gson.GsonBuilder
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BASIC
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.dpastukhov.rickandmorty.BuildConfig
import ru.dpastukhov.rickandmorty.data.ApiService
import ru.dpastukhov.rickandmorty.data.model.Character
import ru.dpastukhov.rickandmorty.data.model.CharacterDeserializer
import javax.inject.Singleton


private val BASE_URL = "https://rickandmortyapi.com/api/"

@Module
object RestModule {

    private val gson = GsonBuilder()
        .setPrettyPrinting()
      //  .registerTypeAdapter(Character::class.java, CharacterDeserializer())
        .create()

    private fun getClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = if (BuildConfig.DEBUG) BODY else BASIC
        val okHttp: OkHttpClient.Builder = OkHttpClient.Builder()
        okHttp.addInterceptor(logging)
        return okHttp.build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(getClient())
        .build()

    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit): ApiService =
        retrofit.create(ApiService::class.java)
}
