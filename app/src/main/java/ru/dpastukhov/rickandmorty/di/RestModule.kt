package ru.dpastukhov.rickandmorty.di

import com.google.gson.GsonBuilder
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BASIC
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import ru.dpastukhov.rickandmorty.BuildConfig
import ru.dpastukhov.rickandmorty.data.repo.CharacterRepository
import ru.dpastukhov.rickandmorty.domain.ApiService
import javax.inject.Singleton


private val BASE_URL = "https://rickandmortyapi.com/api/"

@Module
object RestModule {

    private val gson = GsonBuilder()
        .setPrettyPrinting()
        //  .registerTypeAdapter(Character::class.java, CharacterDeserializer())
        .create()

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

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
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        //  .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(getClient())
        .build()

    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideRepo(api: ApiService): CharacterRepository = CharacterRepository(api)
}
