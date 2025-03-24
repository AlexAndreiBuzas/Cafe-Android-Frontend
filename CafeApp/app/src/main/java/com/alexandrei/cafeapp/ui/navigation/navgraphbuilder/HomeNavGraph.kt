package com.alexandrei.cafeapp.ui.navigation.navgraphbuilder

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.alexandrei.cafeapp.ui.navigation.Screen
import com.alexandrei.cafeapp.ui.screens.home.HomeScreen

fun NavGraphBuilder.homeNavGraph(navController: NavHostController) {
    composable(Screen.Home.route) { HomeScreen(navController) }
}