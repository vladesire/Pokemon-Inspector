package com.vladesire.pokemon.data.network.model

data class PokemonListItem(
    val name: String,
    val url: String
)

data class PokemonListResponse(
    val results: List<PokemonListItem>
)