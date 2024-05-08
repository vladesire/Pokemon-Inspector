package com.vladesire.pokemon.data

import com.vladesire.pokemon.data.network.PokemonRemoteDataSource
import javax.inject.Inject

class DefaultPokemonRepository @Inject constructor(
    pokemon: PokemonRemoteDataSource
) : PokemonRepository {

}