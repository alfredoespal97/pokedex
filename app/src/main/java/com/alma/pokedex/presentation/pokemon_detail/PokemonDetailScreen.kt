package com.alma.pokedex.presentation.pokemon_detail

import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.material3.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter

@Composable
fun PokemonDetailScreen(
    pokemonName: String,
    viewModel: PokemonDetailViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    // Ejecutar solo una vez al iniciar
    LaunchedEffect(pokemonName) {
        viewModel.loadPokemonDetail(pokemonName)
    }

    when {
        state.isLoading -> {
            CircularProgressIndicator()
        }
        state.error.isNotBlank() -> {
            Text(text = "Error: ${state.error}")
        }
        state.pokemon != null -> {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Image(
                    painter = rememberAsyncImagePainter(state.pokemon!!.imageUrl),
                    contentDescription = null,
                    modifier = Modifier.size(120.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "Nombre: ${state.pokemon!!.name}", style = MaterialTheme.typography.headlineSmall)
                // Puedes agregar más datos aquí si los incluyes en el modelo
            }
        }
    }
}