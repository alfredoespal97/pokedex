package com.alma.pokedex.data.remote.dto

data class PokemonListDto(
    val results: List<PokemonResultDto>
)

data class PokemonResultDto(
    val name: String,
    val url: String
)
