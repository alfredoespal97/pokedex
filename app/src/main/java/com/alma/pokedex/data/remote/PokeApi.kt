package com.alma.pokedex.data.remote

import retrofit2.http.Query
import com.alma.pokedex.data.remote.dto.PokemonDto
import com.alma.pokedex.data.remote.dto.PokemonListDto
import retrofit2.http.GET
import retrofit2.http.Path

interface PokeApi {

    @GET("pokemon")
    suspend fun getPokemonList(@Query("limit") limit: Int, @Query("offset") offset: Int ): PokemonListDto

    @GET("pokemon/{name}")
    suspend fun getPokemonDetail(@Path("name") name: String): PokemonDto
}
