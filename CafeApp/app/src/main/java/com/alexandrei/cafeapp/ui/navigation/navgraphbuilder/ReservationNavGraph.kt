package com.alexandrei.cafeapp.ui.navigation.navgraphbuilder

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.alexandrei.cafeapp.ui.navigation.Screen
import com.alexandrei.cafeapp.ui.screens.reservations.ReservationScreen

fun NavGraphBuilder.reservationNavGraph(navController: NavHostController) {
    composable(Screen.Reservation.route) { ReservationScreen(navController) }
}