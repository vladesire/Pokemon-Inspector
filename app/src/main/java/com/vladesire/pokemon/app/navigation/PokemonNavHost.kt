package com.vladesire.pokemon.app.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.vladesire.pokemon.app.PokemonAppState
import com.vladesire.pokemon.ui.screens.details.PokemonDetailsRoute
import com.vladesire.pokemon.ui.screens.details.PokemonDetailsViewModel
import com.vladesire.pokemon.ui.screens.list.PokemonListRoute

@Composable
fun PokemonNavHost(
    appState: PokemonAppState,
    modifier: Modifier = Modifier
) {
    val navController = appState.navController

    NavHost(
        navController = navController,
        startDestination = Screen.PokemonList.route,
        modifier = modifier
    ) {
        composable(
            route = Screen.PokemonList.route
        ) {
            PokemonListRoute(
                showPokemonDetails = { id ->
                    navController.navigate(Screen.PokemonDetails.getRouteWithArgs(id.toString()))
                }
            )
        }

        composable(
            route = Screen.PokemonDetails.route,
            arguments = Screen.PokemonDetails.arguments
        ) { backStackEntry ->
            val id: Int =
                backStackEntry.arguments?.getInt(Screen.PokemonDetails.arguments.first().name)!!

            PokemonDetailsRoute(
                viewModel = hiltViewModel { factory: PokemonDetailsViewModel.Factory ->
                    factory.create(id)
                }
            )
        }
    }
}