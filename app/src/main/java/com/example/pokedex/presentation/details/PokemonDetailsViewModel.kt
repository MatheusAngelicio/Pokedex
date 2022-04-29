package com.example.pokedex.presentation.details

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedex.data.model.Pokemon
import com.example.pokedex.data.remote.PokemonRepository
import com.example.pokedex.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonDetailsViewModel
@Inject constructor(private val repository: PokemonRepository) : ViewModel() {

    private val _getPokemonProperty = MutableLiveData<Pokemon>()
    val getPokemonProperty: LiveData<Pokemon> = _getPokemonProperty

    private val _error = MutableLiveData<Boolean>()
    val error: LiveData<Boolean> = _error

    val isLoading = MutableLiveData(false)

    fun getPokemonById(id: Int) {
        viewModelScope.launch {
            isLoading.postValue(true)
            val pokemon = repository.getPokemonById(id)
            when (pokemon) {
                is Resource.Success -> {
                    pokemon.data?.let {
                        _getPokemonProperty.postValue(it)
                    }
                    isLoading.postValue(false)
                }
                is Resource.Error -> {
                    _error.postValue(true)
                    isLoading.postValue(false)
                }
            }
        }
    }


}