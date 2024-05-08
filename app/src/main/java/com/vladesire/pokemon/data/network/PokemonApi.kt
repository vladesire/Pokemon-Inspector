package com.vladesire.pokemon.data.network

import com.vladesire.pokemon.data.network.model.PokemonDetailsResponse
import com.vladesire.pokemon.data.network.model.PokemonListResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonApi {
    @GET("pokemon")
    suspend fun getPokemons(@Query("limit") limit: Int, @Query("offset") offset: Int): PokemonListResponse

    @GET("pokemon/{id}")
    suspend fun getPokemon(@Path("id") id: Int): PokemonDetailsResponse
}