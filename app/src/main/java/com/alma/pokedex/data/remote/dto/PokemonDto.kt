package com.alma.pokedex.data.remote.dto

import com.google.gson.annotations.SerializedName

data class PokemonDto(
    val name: String,
    val sprites: SpritesDto
)

data class SpritesDto(
    @SerializedName("front_default")
    val frontDefault: String
)
