package com.example.pokemon.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.home.HomeScreen
import com.example.home.detailpokemon.ui.PokemonDetailScreen

fun NavGraphBuilder.homeNavigation(navController: NavController) {
    composable<HomeRoutes.Home> {
        HomeScreen(
            toDetail = { name ->
                navController.navigate(HomeRoutes.DetailPokemon(name))
            }
        )
    }

    composable<HomeRoutes.DetailPokemon> {
        val args = it.toRoute<HomeRoutes.DetailPokemon>()
        PokemonDetailScreen(
            name = args.name,
            onBackClick = { navController.navigate(HomeRoutes.Home) }
        )
    }
}