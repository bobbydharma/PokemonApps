package com.example.core_network.model

import androidx.annotation.Keep

@Keep
data class PokemonListResponse(
    val results: List<PokemonResult>?
)

@Keep
data class PokemonResult(
    val name: String?,
    val url: String?
)

@Keep
data class PokemonDetailResponse(
    val id: Int?,
    val name: String?,
    val abilities: List<AbilityWrapper>?
)

@Keep
data class AbilityWrapper(
    val ability: Ability?
)

@Keep
data class Ability(
    val name: String?
)