package com.alexandrei.cafeapp.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.alexandrei.cafeapp.ui.screens.home.HomeScreen
import com.alexandrei.cafeapp.ui.screens.login.LoginScreen

fun NavGraphBuilder.homeNavGraph(navController: NavHostController) {
    composable(Screen.Home.route) { HomeScreen(navController) }
}