package com.alexandrei.cafeapp.ui.navigation.navgraphbuilder

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.alexandrei.cafeapp.ui.screens.reservations.ReservationDetailScreen

fun NavGraphBuilder.reservationDetailNavGraph(navController: NavHostController) {
    composable("reservationDetail/{seats}") { backStackEntry ->
        val seats = backStackEntry.arguments?.getString("seats")?.toIntOrNull() ?: 2
        ReservationDetailScreen(seats = seats, navController = navController)
    }
}