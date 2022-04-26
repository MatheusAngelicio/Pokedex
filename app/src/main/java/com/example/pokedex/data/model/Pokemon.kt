package com.example.pokedex.data.model

import java.io.Serializable

data class Pokemon(
    val name: String?,
    val height: Int?,
    val weight: Int?,
    val stats: List<Stats>?,
    val moves: List<Moves>?,
    val types: List<Types>?,
    val sprites: Sprites?,
    val abilities: List<Abilities>?
): Serializable

data class Moves(
    val move : Move
): Serializable

data class Move (
    val name : String
): Serializable

data class Abilities(
    val ability: Ability,
    val is_hidden: Boolean,
    val slot: Int
): Serializable

data class Ability (
    val name : String
): Serializable

data class Sprites(
    val back_default: String,
    val back_female: String,
    val back_shiny: String,
    val back_shiny_female: String,
    val front_default: String,
    val front_female: String,
    val front_shiny: String,
    val front_shiny_female: String
): Serializable

data class Stat (
    val name : String,
    val url : String
): Serializable

data class Stats(
    val base_stat: Int,
    val effort: Int,
    val stat: Stat
): Serializable

data class Type(
    val name: String,
    val url: String
): Serializable

data class Types(
    val slot: Int,
    val type: Type
): Serializable