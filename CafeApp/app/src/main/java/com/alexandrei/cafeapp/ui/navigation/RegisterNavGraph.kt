package com.alexandrei.cafeapp.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.alexandrei.cafeapp.ui.screens.home.HomeScreen
import com.alexandrei.cafeapp.ui.screens.register.RegisterScreen
import com.alexandrei.cafeapp.ui.screens.register.RegisterViewModel

fun NavGraphBuilder.registerNavGraph(navController: NavHostController) {
    composable(Screen.Register.route) { RegisterScreen(navController) }
}