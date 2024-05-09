package com.vladesire.pokemon

import com.vladesire.pokemon.data.model.DetailedPokemon
import com.vladesire.pokemon.data.model.Pokemon
import com.vladesire.pokemon.data.network.PokemonApi
import com.vladesire.pokemon.data.network.PokemonRemoteDataSource
import com.vladesire.pokemon.data.network.model.PokemonDetailsResponse
import com.vladesire.pokemon.data.network.model.PokemonListItem
import com.vladesire.pokemon.data.network.model.PokemonListResponse
import com.vladesire.pokemon.data.network.model.Sprites
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.Assert.*

class PokemonDataSourceUnitTest {
    private val testDispatcher = StandardTestDispatcher()

    @Test
    fun `check with fake api`() = runTest(testDispatcher) {
        val api = object : PokemonApi {
            override suspend fun getPokemons(limit: Int, offset: Int): PokemonListResponse {
                return PokemonListResponse(
                    results = listOf(
                        PokemonListItem(
                            name = "Wei Wuxian",
                            url = "https://pokeapi.co/api/v2/pokemon/7331/",
                        ),
                        PokemonListItem(
                            name = "Lan Wangji",
                            url = "https://pokeapi.co/api/v2/pokemon/1337/",
                        )
                    )
                )
            }

            override suspend fun getPokemon(id: Int): PokemonDetailsResponse {
                return PokemonDetailsResponse(
                    name = "Wei Wuxian",
                    weight = 60,
                    height = 180,
                    sprites = Sprites(
                        front_default = "weiwuxian1.jpg",
                        back_default = "weiwuxian2.jpg",
                    )
                )
            }
        }

        val pokemon = PokemonRemoteDataSource(
            api = api,
            ioDispatcher = testDispatcher
        )


        assertEquals(
            listOf(
                Pokemon(7331, "Wei Wuxian"),
                Pokemon(1337, "Lan Wangji")
            ),
            pokemon.getPokemons()
        )

        assertEquals(
            DetailedPokemon(
                name = "Wei Wuxian",
                weight = 60,
                height = 180,
                frontImageUrl = "weiwuxian1.jpg",
                backImageUrl = "weiwuxian2.jpg",
            ),
            pokemon.getDetailedPokemon(1)
        )
    }
}