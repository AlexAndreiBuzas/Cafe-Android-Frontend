package com.alexandrei.cafeapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

@Composable
fun NavGraph(navController: NavHostController) {

    NavHost(navController = navController, startDestination = Screen.Login.route) {
        loginNavGraph(navController)
        registerNavGraph(navController)
        homeNavGraph(navController)
        orderNavGraph(navController)
    }
}