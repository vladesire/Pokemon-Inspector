package com.vladesire.pokemon.data

import com.vladesire.pokemon.data.network.PokemonRemoteDataSource
import javax.inject.Inject

class DefaultPokemonRepository @Inject constructor(
    private val pokemon: PokemonRemoteDataSource
) : PokemonRepository {
    override suspend fun getPokemons() = pokemon.getPokemons()
    override suspend fun getPokemonImageUrl(id: Int) = pokemon.getImageUrl(id)
    override suspend fun getDetailedPokemon(id: Int) = pokemon.getDetailedPokemon(id)
}