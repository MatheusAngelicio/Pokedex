package com.example.pokedex.presentation.home.fragments

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedex.data.PokemonRepository
import com.example.pokedex.data.Results
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel
@Inject constructor(private val repository: PokemonRepository) : ViewModel() {

    init {
        getCharacters(20,0)
    }

    private val _responseApi = MutableLiveData<List<Results>>()
    val responseApi: LiveData<List<Results>> = _responseApi


    fun getCharacters(limit: Int, offset: Int) = viewModelScope.launch {
        runCatching {
            repository.getPokemonList(limit, offset)
        }.onSuccess { pokemonList ->
            Log.d("DEU BOA", "AAAAAAAAAAAE $pokemonList")
        }.onFailure {
            Log.d("MERDA", "DEU MERDA $it")
        }
    }
}