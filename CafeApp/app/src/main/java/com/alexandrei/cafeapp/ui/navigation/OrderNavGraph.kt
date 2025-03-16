package com.alexandrei.cafeapp.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.alexandrei.cafeapp.ui.screens.login.LoginScreen

fun NavGraphBuilder.orderNavGraph(navController: NavHostController) {
    composable(Screen.Login.route) { LoginScreen(navController) }
}