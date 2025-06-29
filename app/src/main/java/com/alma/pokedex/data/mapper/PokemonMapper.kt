package com.alma.pokedex.data.mapper

import com.alma.pokedex.data.remote.dto.PokemonDto
import com.alma.pokedex.data.remote.dto.PokemonResultDto
import com.alma.pokedex.domain.model.Pokemon

/* Utilidad para extraer el ID numérico de la URL */
internal fun extractIdFromUrl(url: String): String =
    url.trimEnd('/').substringAfterLast('/')

/* ---------- Lista (PokemonResultDto) ---------- */
fun PokemonResultDto.toDomain(): Pokemon {
    val id = extractIdFromUrl(url)
    return Pokemon(
        id = id.toLongOrNull(),
        name = name,
        /* imagen simple para la miniatura de la grilla */
        imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$id.png"
        /* El resto de campos se dejan nulos; se llenarán cuando pidamos el detalle */
    )
}

/* ---------- Detalle (PokemonDto) ---------- */
fun PokemonDto.toDomain(): Pokemon =
    Pokemon(
        /* ‑‑ campo auxiliar para la UI ‑‑ */
        imageUrl = sprites?.front_default,
        /* Copiamos (casi) todos los campos tal cual; los DTO y dominio comparten estructura */
        abilities = abilities,
        baseExperience = base_experience,
        cries = cries,
        forms = forms,
        gameIndices = game_indices,
        height = height,
        heldItems = held_items,
        id = id,
        isDefault = is_default,
        locationAreaEncounters = location_area_encounters,
        moves = moves,
        name = name,
        order = order,
        pastAbilities = past_abilities,
        pastTypes = past_types,
        species = species,
        sprites = sprites,
        stats = stats,
        types = types,
        weight = weight
    )
