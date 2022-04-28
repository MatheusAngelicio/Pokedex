package com.example.pokedex.util

import android.view.View
import com.example.pokedex.R
import java.math.RoundingMode
import java.text.DecimalFormat

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

fun convertValue(convert: Int?): String {
    convert?.let {
        return try {
            val value = ((it.toDouble()) / 10)
            val df = DecimalFormat("#.#")
            df.roundingMode = RoundingMode.CEILING
            df.format(value).replace(",", ".").toDouble().toString()
        } catch (e: Exception) {
            "0"
        }
    } ?: run {
        return "0"
    }
}

fun getTypePokemon(type: String): Int {
    return when (type) {
        "normal" -> R.drawable.type_normal
        "fighting" -> R.drawable.type_fighting
        "flying" -> R.drawable.type_flying
        "poison" -> R.drawable.type_poison
        "ground" -> R.drawable.type_ground
        "rock" -> R.drawable.type_rock
        "bug" -> R.drawable.type_bug
        "ghost" -> R.drawable.type_ghost
        "steel" -> R.drawable.type_steel
        "fire" -> R.drawable.type_fire
        "water" -> R.drawable.type_water
        "grass" -> R.drawable.type_grass
        "electric" -> R.drawable.type_electric
        "psychic" -> R.drawable.type_psychic
        "ice" -> R.drawable.type_ice
        "dragon" -> R.drawable.type_dragon
        "dark" -> R.drawable.type_dark
        "fairy" -> R.drawable.type_fairy
        "unknown" -> R.drawable.type_normal
        "shadow" -> R.drawable.type_ghost
        else -> R.drawable.type_normal
    }
}