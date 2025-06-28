package com.alma.pokedex.domain.usecase

import com.alma.pokedex.domain.model.Pokemon
import com.alma.pokedex.domain.repository.PokemonRepository
import javax.inject.Inject

class GetPokemonDetailUseCase @Inject constructor(
    private val repository: PokemonRepository
) {
    suspend operator fun invoke(name: String): Pokemon {
        return repository.getPokemonDetail(name)
    }
}