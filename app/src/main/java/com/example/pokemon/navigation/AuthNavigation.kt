package com.example.pokemon.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.auth.login.ui.LoginScreen
import com.example.auth.register.ui.RegisterScreen

fun NavGraphBuilder.authNavigation(navController: NavController) {

    composable<AuthRoutes.Login> {
        LoginScreen(
            toHome = {
                navController.navigate(HomeRoutes.Home) {
                    popUpTo(0)
                    launchSingleTop = true
                }
            },
            toRegister = {
                navController.navigate(AuthRoutes.Register) {
                    popUpTo(AuthRoutes.Login) { inclusive = true }
                    launchSingleTop = true
                }
            }
        )
    }

    composable<AuthRoutes.Register> {
        RegisterScreen(
            toLogin = {
                navController.navigate(AuthRoutes.Login) {
                    popUpTo(AuthRoutes.Register)
                    launchSingleTop = true
                }
            }
        )
    }

}
