package com.vladesire.pokemon.ui.screens.list

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vladesire.pokemon.data.PokemonRepository
import com.vladesire.pokemon.data.model.Pokemon
import com.vladesire.pokemon.ui.screens.list.PokemonListScreenUIState.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed interface PokemonListScreenUIState {
    data object Loading : PokemonListScreenUIState
    data class Loaded(
        val pokemons: List<Pokemon>
    ) : PokemonListScreenUIState
}

fun <T> List<T>.updated(index: Int, transform: (T) -> T): List<T> {
    return this.toMutableList().also { it[index] = transform(it[index]) }
}

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val repository: PokemonRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow<PokemonListScreenUIState>(Loading)
    val uiState = _uiState.asStateFlow()

    init {
        // Basic error handling
        try {
            viewModelScope.launch {
                val pokemons = repository.getPokemons()

                _uiState.value = Loaded(pokemons)

                pokemons.forEachIndexed { index, pokemon ->
                    launch {
                        val url = repository.getPokemonImageUrl(pokemon.id)

                        _uiState.update {
                            (it as Loaded).copy(
                                pokemons = it.pokemons.updated(index) {
                                    it.copy(
                                        imageUrl = url
                                    )
                                }
                            )
                        }
                    }
                }
            }
        } catch (ex: Exception) {
            Log.e("PokemonListViewModel", "$ex")
        }
    }
}