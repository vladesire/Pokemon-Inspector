package com.vladesire.pokemon.data

import com.vladesire.pokemon.data.model.DetailedPokemon
import com.vladesire.pokemon.data.model.Pokemon

interface PokemonRepository {
    suspend fun getPokemons(): List<Pokemon>
    suspend fun getPokemonImageUrl(id: Int): String
    suspend fun getDetailedPokemon(id: Int): DetailedPokemon
}