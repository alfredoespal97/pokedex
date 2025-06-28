package com.alma.pokedex.domain.usecase

import com.alma.pokedex.domain.model.Pokemon
import com.alma.pokedex.domain.repository.PokemonRepository
import javax.inject.Inject

class GetPokemonListUseCase @Inject constructor(
    private val repository: PokemonRepository
) {
    suspend operator fun invoke(limit: Int, offset: Int): List<Pokemon> {
        return repository.getPokemonList(limit, offset)
    }
}