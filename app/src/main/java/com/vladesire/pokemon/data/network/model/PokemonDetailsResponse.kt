package com.vladesire.pokemon.data.network.model

data class Sprites(
    val front_default: String,
    val back_default: String
)

data class PokemonDetailsResponse(
    val name: String,
    val weight: Int,
    val height: Int,
    val sprites: Sprites
)