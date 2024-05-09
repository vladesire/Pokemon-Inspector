package com.vladesire.pokemon.ui.screens.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonDetailsScreen(
    pokemon: DetailedPokemon,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(pokemon.name, fontSize = 32.sp, fontWeight = FontWeight.Bold)
                }
            )
        },
        modifier = modifier
    ) { pv ->
        LazyColumn(
            contentPadding = PaddingValues(horizontal = 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(pv)
        ) {
            item {
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    AsyncImage(
                        model = pokemon.frontImageUrl,
                        contentDescription = null,
                        modifier = Modifier.size(150.dp)
                    )
                    AsyncImage(
                        model = pokemon.backImageUrl,
                        contentDescription = null,
                        modifier = Modifier.size(150.dp)
                    )
                }
            }
            item {
                Text("height: ${pokemon.height}")
            }
            item {
                Text("weight: ${pokemon.weight}")
            }
        }
    }

}
