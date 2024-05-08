package com.vladesire.pokemon.di

import com.vladesire.pokemon.data.DefaultPokemonRepository
import com.vladesire.pokemon.data.PokemonRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class PokemonModule {
    @Binds
    abstract fun bindsPokemonRepository(
        defaultPokemonRepository: DefaultPokemonRepository
    ): PokemonRepository
}