package com.example.pokedex.di

import com.example.pokedex.BuildConfig
import com.example.pokedex.data.remote.PokeApi
import com.example.pokedex.data.remote.PokemonRepository
import com.example.pokedex.util.TIMEOUT_SECONDS
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {



    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class BaseUrl

    @Provides
    @BaseUrl
    fun baseUrl() = "https://pokeapi.co/api/v2/"

    @Provides
    fun okHttpProvider(): OkHttpClient = OkHttpClient().newBuilder().apply {
        connectTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
        readTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
        writeTimeout(
            TIMEOUT_SECONDS, TimeUnit.SECONDS
        ) //            .addInterceptor(mAuthInterceptor)

        val logInterceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        if (BuildConfig.DEBUG) {
            addInterceptor(logInterceptor)
        }
    }.build()

    @Provides
    @Singleton
    fun retrofitProvider(@BaseUrl baseUrl: String, okHttpClient: OkHttpClient): Retrofit {
        val kotlinJsonAdapter = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()

        return Retrofit.Builder().baseUrl(baseUrl).client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(kotlinJsonAdapter)).build()
    }

    @Provides
    @Singleton
    fun serviceProvider(retrofit: Retrofit): PokeApi = retrofit.create(PokeApi::class.java)



}