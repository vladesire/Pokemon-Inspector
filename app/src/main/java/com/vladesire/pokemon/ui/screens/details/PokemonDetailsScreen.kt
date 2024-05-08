package com.vladesire.pokemon.ui.screens.details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.vladesire.pokemon.data.model.DetailedPokemon
import com.vladesire.pokemon.ui.LoadingBox

@Composable
fun PokemonDetailsRoute(
    viewModel: PokemonDetailsViewModel
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    when (val state = uiState) {
        is PokemonDetailsScreenUIState.Loaded -> {
            PokemonDetailsScreen(
                pokemon = state.pokemon
            )
        }
        PokemonDetailsScreenUIState.Loading -> {
            LoadingBox()
        }
    }
}


@Composable
fun PokemonDetailsScreen(
    pokemon: DetailedPokemon,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(pokemon.name, fontSize = 32.sp, fontWeight = FontWeight.Bold)

        Row {
            AsyncImage(
                model = pokemon.frontImageUrl,
                contentDescription = null,
                modifier = Modifier.size(128.dp)
            )
            AsyncImage(
                model = pokemon.backImageUrl,
                contentDescription = null,
                modifier = Modifier.size(128.dp)
            )
        }

        Text("height: ${pokemon.height}")
        Text("weight: ${pokemon.weight}")
    }
}
