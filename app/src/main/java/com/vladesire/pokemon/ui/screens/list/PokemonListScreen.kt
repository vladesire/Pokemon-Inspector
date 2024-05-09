package com.vladesire.pokemon.ui.screens.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.vladesire.pokemon.R
import com.vladesire.pokemon.data.model.Pokemon
import com.vladesire.pokemon.ui.ErrorBox
import com.vladesire.pokemon.ui.LoadingBox

@Composable
fun PokemonListRoute(
    viewModel: PokemonListViewModel = hiltViewModel(),
    showPokemonDetails: (id: Int) -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    when (val state = uiState) {
        is PokemonListScreenUIState.Loaded -> {
            PokemonListScreen(
                pokemons = state.pokemons,
                showPokemonDetails = showPokemonDetails,
            )
        }

        is PokemonListScreenUIState.Error -> {
            ErrorBox(state.message)
        }

        PokemonListScreenUIState.Loading ->  {
            LoadingBox()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonListScreen(
    pokemons: List<Pokemon>,
    showPokemonDetails: (id: Int) -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Pokemon Inspector")
                }
            )
        }
    ) { pv ->
        LazyColumn(
            modifier = Modifier.padding(pv),
            contentPadding = PaddingValues(horizontal = 8.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            items(
                pokemons,
            ) {
                Card(
                    onClick = { showPokemonDetails(it.id) }
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(24.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(4.dp)
                    ) {
                        AsyncImage(
                            model = it.imageUrl,
                            placeholder = painterResource(R.drawable.no_pokemon),
                            contentDescription = null,
                            modifier = Modifier.size(80.dp)
                        )
                        Text(
                            text = it.name,
                            fontSize = 32.sp,
                            fontWeight = FontWeight.Bold,
                        )
                    }
                }
            }
        }
    }
}