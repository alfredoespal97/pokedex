package com.alma.pokedex.presentation.pokedex

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PokemonListScreen(
    viewModel: PokemonListViewModel = hiltViewModel(),
    onPokemonClick: (String) -> Unit
) {
    val state by viewModel.state.collectAsState()

    if (state.isLoading) {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else if (state.error.isNotBlank()) {
        Text("Error: ${state.error}")
    } else {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(state.pokemonList) { pokemon ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { pokemon.name?.let { onPokemonClick(it) } },
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(8.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painter = rememberAsyncImagePainter(pokemon.imageUrl),
                            contentDescription = pokemon.name,
                            modifier = Modifier.size(100.dp)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        pokemon.name?.replaceFirstChar { it.uppercase() }
                            ?.let { Text(text = it, style = MaterialTheme.typography.bodyLarge) }
                    }
                }
            }
        }
    }
}

//@Composable
//fun PokemonListScreen(
//    viewModel: PokemonListViewModel = hiltViewModel(),
//    onPokemonClick: (String) -> Unit
//) {
//    val state by viewModel.state.collectAsState()
//
//    LazyColumn {
//        items(state.pokemonList) { pokemon ->
//            ListItem(
//                headlineContent = { Text(pokemon.name) },
//                modifier = Modifier
//                    .clickable { onPokemonClick(pokemon.name) }
//                    .padding(8.dp)
//            )
//        }
//    }
//}
