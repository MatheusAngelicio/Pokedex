package com.example.pokedex.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedex.data.PokemonRepository
import com.example.pokedex.data.model.Results
import com.example.pokedex.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel
@Inject constructor(private val repository: PokemonRepository) : ViewModel() {

    private val _responseApi = MutableLiveData<List<Results>>()
    val responseApi: LiveData<List<Results>> = _responseApi

    val isLoading = MutableLiveData(false)


    fun loadPokemonPaginated() {
        viewModelScope.launch {
            isLoading.value = true
            val result = repository.getPokemonList(20, 0)
            when (result) {
                is Resource.Success -> {
                   _responseApi.value = result.data?.results
                    isLoading.value = false
                }
                is Resource.Error -> Unit
            }
        }
    }
}