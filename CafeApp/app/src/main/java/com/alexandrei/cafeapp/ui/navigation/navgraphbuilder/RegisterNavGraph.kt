package com.alexandrei.cafeapp.ui.navigation.navgraphbuilder

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.alexandrei.cafeapp.ui.navigation.Screen
import com.alexandrei.cafeapp.ui.screens.register.RegisterScreen

fun NavGraphBuilder.registerNavGraph(navController: NavHostController) {
    composable(Screen.Register.route) { RegisterScreen(navController) }
}