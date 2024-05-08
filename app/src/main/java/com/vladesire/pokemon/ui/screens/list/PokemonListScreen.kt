package com.vladesire.pokemon.ui.screens.list

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun PokemonListRoute(
    viewModel: PokemonListViewModel = hiltViewModel(),
    showPokemonDetails: (id: Int) -> Unit
) {
    Column {
        Text("Pokemon List")
        Button(onClick = { showPokemonDetails(1) }) {
            Text(text = "Show pokemon 1")
        }
    }
    
}

@Composable
fun PokemonListScreen(modifier: Modifier = Modifier) {

}