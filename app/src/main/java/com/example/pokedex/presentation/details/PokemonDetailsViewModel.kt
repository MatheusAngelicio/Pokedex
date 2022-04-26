package com.example.pokedex.presentation.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedex.data.model.Pokemon
import com.example.pokedex.data.remote.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonDetailsViewModel
@Inject constructor(private val repository: PokemonRepository) : ViewModel() {

    val getPokemonProperty = MutableLiveData<Pokemon>()


    fun getPokemonById(id: Int) {
        viewModelScope.launch {
            val pokemon = repository.getPokemonById(id)
        }
    }


}