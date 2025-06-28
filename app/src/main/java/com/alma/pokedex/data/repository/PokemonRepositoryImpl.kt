package com.alma.pokedex.data.repository

import com.alma.pokedex.data.mapper.toDomain
import com.alma.pokedex.data.remote.PokeApi
import com.alma.pokedex.domain.model.Pokemon
import com.alma.pokedex.domain.repository.PokemonRepository

class PokemonRepositoryImpl(
    private val api: PokeApi
) : PokemonRepository {

    override suspend fun getPokemonList(limit: Int, offset: Int): List<Pokemon> {
        return api.getPokemonList(limit, offset).results.map { it.toDomain() }
    }

    override suspend fun getPokemonDetail(name: String): Pokemon {
        return api.getPokemonDetail(name).toDomain()
    }
}
