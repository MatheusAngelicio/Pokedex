package com.example.pokedex.data

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
}