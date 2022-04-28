package com.example.pokedex.data.remote

import com.example.pokedex.data.model.Pokemon
import com.example.pokedex.data.model.PokemonList
import com.example.pokedex.util.Resource
import java.lang.Exception
import javax.inject.Inject

class PokemonRepository
@Inject constructor(private val api: PokeApi) {
    suspend fun getPokemonList(limit: Int, offset: Int): Resource<PokemonList> {
        val response = try {
            api.getPokemonList(limit, offset)
        } catch (e: Exception) {
            return Resource.Error("An unknow error occured : $e")
        }
        return Resource.Success(response)
    }

    suspend fun getPokemonById(id: Int): Resource<Pokemon> {
        val response = try {
            api.getPokemonById(id)
        } catch (e: Exception) {
            return Resource.Error("An unknow error occured : $e")
        }
        return Resource.Success(response)
    }
}