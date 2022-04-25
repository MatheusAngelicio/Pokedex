package com.example.pokedex.data.model

data class PokemonList(
    val count: Int,
    val next: String,
    val previous: String?,
    val results: List<Results>
)