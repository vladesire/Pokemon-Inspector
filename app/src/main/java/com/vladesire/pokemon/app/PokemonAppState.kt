package com.vladesire.pokemon.app

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun rememberPokemonAppState(
    navController: NavHostController = rememberNavController()
) = remember(navController) {
    PokemonAppState(navController)
}

@Stable
class PokemonAppState(
    val navController: NavHostController
)