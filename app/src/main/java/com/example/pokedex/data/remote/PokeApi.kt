package com.example.pokedex.data.remote

import com.example.pokedex.data.model.Pokemon
import com.example.pokedex.data.model.PokemonList
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokeApi {

    @GET("pokemon")
    suspend fun getPokemonList(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): PokemonList

    @GET("pokemon/{id}")
    suspend fun getPokemonById(
        @Path("id") id: Int
    ):Pokemon

}