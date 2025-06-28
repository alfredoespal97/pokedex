package com.alma.pokedex.presentation.pokedex

import com.alma.pokedex.domain.model.Pokemon

data class PokemonListState(
    val isLoading: Boolean = false,
    val pokemonList: List<Pokemon> = emptyList(),
    val error: String = ""
)
