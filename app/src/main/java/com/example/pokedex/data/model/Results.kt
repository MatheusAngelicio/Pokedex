package com.example.pokedex.data.model

import java.io.Serializable


data class Results(
    val name: String,
    val url: String,
    var number: Int?,
    var imageUrl: String?
): Serializable