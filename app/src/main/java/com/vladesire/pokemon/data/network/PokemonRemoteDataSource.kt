package com.vladesire.pokemon.data.network

import com.vladesire.pokemon.data.model.DetailedPokemon
import com.vladesire.pokemon.data.model.Pokemon
import com.vladesire.pokemon.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PokemonRemoteDataSource @Inject constructor(
    private val api: PokemonApi,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) {
    suspend fun getPokemons() = withContext(ioDispatcher) {
        api
            .getPokemons(100, 0)
            .results
            .map {
                Pokemon(
                    id = it.url.split('/').let { it[it.size-2].toInt() },
                    name = it.name
                )
            }
    }

    suspend fun getImageUrl(id: Int) = withContext(ioDispatcher) {
        api.getPokemon(id).sprites.front_default
    }

    suspend fun getDetailedPokemon(id: Int) = withContext(ioDispatcher) {
        api.getPokemon(id).let {
            DetailedPokemon(
                name = it.name,
                frontImageUrl = it.sprites.front_default,
                backImageUrl = it.sprites.back_default,
                height = it.height,
                weight = it.weight,
            )
        }
    }
}