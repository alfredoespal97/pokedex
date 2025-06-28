package com.alma.pokedex.data.mapper

import com.alma.pokedex.data.remote.dto.PokemonDto
import com.alma.pokedex.data.remote.dto.PokemonResultDto
import com.alma.pokedex.domain.model.Pokemon

fun PokemonResultDto.toDomain(): Pokemon {
    return Pokemon(
        name = this.name,
        imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${extractIdFromUrl(url)}.png"
    )
}

fun PokemonDto.toDomain(): Pokemon {
    return Pokemon(
        name = this.name,
        imageUrl = this.sprites.frontDefault
    )
}

// Extra helper
private fun extractIdFromUrl(url: String): String {
    return url.trimEnd('/').split("/").last()
}