package com.vladesire.pokemon.ui.screens.details

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun PokemonDetailsRoute(
    viewModel: PokemonDetailsViewModel
) {
    Text("Pokemon Details: ${viewModel.pokemonId}")
}


@Composable
fun PokemonDetailsScreen(modifier: Modifier = Modifier) {

}
