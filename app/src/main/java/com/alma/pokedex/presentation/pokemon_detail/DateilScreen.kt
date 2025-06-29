package com.alma.pokedex.presentation.pokemon_detail

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonDetailScreen2(
    pokemonName: String,
    onBackClick: () -> Unit, // Manteniendo onBackClick como lo tenías
    viewModel: PokemonDetailViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(pokemonName) {
        viewModel.loadPokemonDetail(pokemonName)
    }

    when {
        state.isLoading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        state.error.isNotBlank() -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Error: ${state.error}")
            }
        }

        state.pokemon != null -> {
            val pokemon = state.pokemon!! // Para no repetir state.pokemon!! constantemente

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 16.dp), // Mantiene la Card arriba
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                    shape = MaterialTheme.shapes.large
                ) {
                    Box(modifier = Modifier.fillMaxWidth()) {
                        IconButton(
                            onClick = onBackClick,
                            modifier = Modifier
                                .align(Alignment.TopStart)
                                .padding(8.dp)
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Volver al listado",
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }

                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(
                                    top = 56.dp,
                                    start = 16.dp,
                                    end = 16.dp,
                                    bottom = 16.dp
                                ),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            AsyncImage(
                                model = ImageRequest.Builder(LocalContext.current)
                                    .data(pokemon.imageUrl)
                                    .crossfade(true)     // Añade tu imagen de error
                                    .build(),
                                contentDescription = "Imagen de ${pokemon.name}",
                                contentScale = ContentScale.Fit,
                                modifier = Modifier
                                    .size(200.dp)
                                    .padding(bottom = 16.dp)
                            )

                            pokemon.name?.let {
                                Text(
                                    text = it.replaceFirstChar { char -> if (char.isLowerCase()) char.titlecase() else char.toString() }, // Capitalizar nombre
                                    style = MaterialTheme.typography.headlineMedium,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                            }

                            Spacer(modifier = Modifier.height(16.dp)) // Más espacio antes de los detalles

                            // Mostrar Tipo(s)
                            pokemon.types?.takeIf { it.isNotEmpty() }?.let { types ->
                                val typesString = types.mapNotNull { it.type?.name?.replaceFirstChar { char -> if (char.isLowerCase()) char.titlecase() else char.toString() } }.joinToString(", ")
                                if (typesString.isNotBlank()) {
                                    Text(text = "Tipo: $typesString", style = MaterialTheme.typography.bodyLarge)
                                    Spacer(modifier = Modifier.height(8.dp))
                                }
                            } ?: Text(text = "Tipo: Desconocido", style = MaterialTheme.typography.bodyLarge)


                            // Mostrar Altura
                            pokemon.height?.let {
                                Text(text = "Altura: ${it / 10.0} m", style = MaterialTheme.typography.bodyLarge) // Asumiendo que la altura está en decímetros
                                Spacer(modifier = Modifier.height(8.dp))
                            }

                            // Mostrar Peso
                            pokemon.weight?.let {
                                Text(text = "Peso: ${it / 10.0} kg", style = MaterialTheme.typography.bodyLarge) // Asumiendo que el peso está en hectogramos
                                Spacer(modifier = Modifier.height(8.dp))
                            }

                            // Mostrar Habilidades
                            pokemon.abilities?.takeIf { it.isNotEmpty() }?.let { abilities ->
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    text = "Habilidades:",
                                    style = MaterialTheme.typography.titleMedium, // Un título para la sección de habilidades
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                abilities.forEach { abilityItem ->
                                    abilityItem.ability?.name?.let { abilityName ->
                                        Text(
                                            text = "- ${abilityName.replaceFirstChar { char -> if (char.isLowerCase()) char.titlecase() else char.toString() }}${if (abilityItem.isHidden == true) " (Oculta)" else ""}",
                                            style = MaterialTheme.typography.bodyLarge,
                                            modifier = Modifier.padding(start = 8.dp) // Pequeña indentación para cada habilidad
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        else -> {
            // Este Box es para centrar el mensaje si pokemon es null y no está cargando ni hay error
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "No se encontró información del Pokémon.")
            }
        }
    }
}