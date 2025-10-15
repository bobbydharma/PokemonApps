package com.example.pokemon.navigation

import kotlinx.serialization.Serializable

object AuthRoutes {
    @Serializable
    data object Login

    @Serializable
    data object Register
}

object HomeRoutes{
    @Serializable
    data object Home
    @Serializable
    data object Profile
    @Serializable
    data class DetailPokemon(
        val name: String
    )
}