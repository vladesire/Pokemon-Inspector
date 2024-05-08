package com.vladesire.pokemon.app.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class Screen(
    private val _route: String,
    val arguments: List<NamedNavArgument>,
) {
    val route: String =
        arguments.fold(_route) { acc, cur -> "$acc/{${cur.name}}" }

    fun getRouteWithArgs(vararg args: String) =
        args.fold(_route) { acc, cur -> "$acc/$cur" }

    data object PokemonList: Screen(
        "pokemon-list",
        emptyList()
    )
    data object PokemonDetails: Screen(
        "pokemon-details",
        listOf(navArgument("id") { type = NavType.IntType })
    )
}