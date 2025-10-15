package com.example.core_network.model

import androidx.annotation.Keep

@Keep
data class PokemonListResponse(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<PokemonResult>?
)

@Keep
data class PokemonResult(
    val name: String?,
    val url: String?
)
