package com.vladesire.pokemon.ui.screens.details

import androidx.lifecycle.ViewModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel(assistedFactory = PokemonDetailsViewModel.Factory::class)
class PokemonDetailsViewModel @AssistedInject constructor(
    @Assisted val pokemonId: Int
) : ViewModel() {

    @AssistedFactory
    interface Factory {
        fun create(pokemonId: Int): PokemonDetailsViewModel
    }

}