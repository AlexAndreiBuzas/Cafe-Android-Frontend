package com.alexandrei.cafeapp.ui.navigation.navgraphbuilder

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.alexandrei.cafeapp.ui.screens.order.CoffeeDetailScreen

fun NavGraphBuilder.orderNavGraph(navController: NavHostController) {
    composable("order/{coffeeId}") { backStackEntry ->
        val coffeeId = backStackEntry.arguments?.getString("coffeeId") ?: ""
        CoffeeDetailScreen(coffeeId = coffeeId, navController = navController)
    }
}