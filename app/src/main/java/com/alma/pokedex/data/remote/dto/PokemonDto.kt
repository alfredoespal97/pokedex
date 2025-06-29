package com.alma.pokedex.data.remote.dto

import com.alma.pokedex.domain.model.Ability
import com.alma.pokedex.domain.model.Cries
import com.alma.pokedex.domain.model.GameIndex
import com.alma.pokedex.domain.model.HeldItem
import com.alma.pokedex.domain.model.Move
import com.alma.pokedex.domain.model.Other
import com.alma.pokedex.domain.model.PastAbility
import com.alma.pokedex.domain.model.Species
import com.alma.pokedex.domain.model.Stat
import com.alma.pokedex.domain.model.Type
import com.alma.pokedex.domain.model.Versions
import com.google.gson.annotations.SerializedName

data class PokemonDto(
    val id: Long?,
    val name: String?,
    val height: Long?,
    val weight: Long?,
    val base_experience: Long?,
    val abilities: List<Ability>?,
    val cries: Cries?,
    val forms: List<Species>?,
    val game_indices: List<GameIndex>?,
    val held_items: List<HeldItem>?,
    val is_default: Boolean?,
    val location_area_encounters: String?,
    val moves: List<Move>?,
    val order: Long?,
    val past_abilities: List<PastAbility>?,
    val past_types: List<Any>?, // puede ser refinado
    val species: Species?,
    val sprites: SpritesDto?,
    val stats: List<Stat>?,
    val types: List<Type>?
)




data class SpritesDto(
    val back_default: String?,
    val back_female: Any?,
    val back_shiny: String?,
    val back_shiny_female: Any?,
    val front_default: String?,
    val front_female: Any?,
    val front_shiny: String?,
    val front_shiny_female: Any?,
    val other: Other?,
    val versions: Versions?,
    val animated: SpritesDto?
)

