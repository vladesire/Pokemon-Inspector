package com.vladesire.pokemon.app

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.vladesire.pokemon.app.navigation.PokemonNavHost

@Composable
fun PokemonApp(
    appState: PokemonAppState
) {
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { padding -> 
        PokemonNavHost(
            appState = appState,
            Modifier.padding(padding)
        )
    }    
}