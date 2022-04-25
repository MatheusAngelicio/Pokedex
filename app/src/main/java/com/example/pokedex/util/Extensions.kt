package com.example.pokedex.util

import android.view.View

fun View.gone() {
    visibility = View.GONE
}

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun formattedNumber(number: Int?): String {
    val formattedNumber = number.toString().padStart(3, '0')
    return "https://assets.pokemon.com/assets/cms2/img/pokedex/detail/$formattedNumber.png"
}