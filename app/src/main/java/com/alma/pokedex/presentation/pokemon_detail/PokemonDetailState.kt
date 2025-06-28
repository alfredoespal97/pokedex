package com.alma.pokedex.presentation.pokemon_detail

import com.alma.pokedex.domain.model.Pokemon

data class PokemonDetailState(
    val isLoading: Boolean = false,
    val pokemon: Pokemon? = null,
    val error: String = ""
)