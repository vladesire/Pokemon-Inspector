package com.vladesire.pokemon.ui.screens.details

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vladesire.pokemon.data.PokemonRepository
import com.vladesire.pokemon.data.model.DetailedPokemon
import com.vladesire.pokemon.ui.screens.details.PokemonDetailsScreenUIState.*
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed interface PokemonDetailsScreenUIState {
    data object Loading : PokemonDetailsScreenUIState
    data class Error(
        val message: String
    ) : PokemonDetailsScreenUIState
    data class Loaded(
        val pokemon: DetailedPokemon
    ) : PokemonDetailsScreenUIState
}

@HiltViewModel(assistedFactory = PokemonDetailsViewModel.Factory::class)
class PokemonDetailsViewModel @AssistedInject constructor(
    @Assisted pokemonId: Int,
    repository: PokemonRepository
) : ViewModel() {

    @AssistedFactory
    interface Factory {
        fun create(pokemonId: Int): PokemonDetailsViewModel
    }

    private val _uiState = MutableStateFlow<PokemonDetailsScreenUIState>(Loading)
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            // Basic error handling
            try {
                _uiState.value = Loaded(repository.getDetailedPokemon(pokemonId))
            } catch (ex: Exception) {
                _uiState.value = Error(ex.toString())
            }
        }
    }
}