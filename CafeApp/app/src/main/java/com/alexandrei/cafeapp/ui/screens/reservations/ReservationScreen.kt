package com.alexandrei.cafeapp.ui.screens.reservations

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.alexandrei.cafeapp.R
import com.alexandrei.cafeapp.ui.components.ItemCard
import com.alexandrei.cafeapp.ui.components.LocationBar
import com.alexandrei.cafeapp.ui.navigation.Screen

@Composable
fun ReservationScreen(navController: NavController, viewModel: ReservationViewModel = hiltViewModel()) {
    val reservations = listOf(
        ReservationItem("Table for Two", "Cozy table for 2 people", 2),
        ReservationItem("Table for Four", "Spacious table for 4 guests", 4),
        ReservationItem("Table for Six", "Large table for a group of 6", 6)
    )

    LaunchedEffect(Unit) {
        viewModel.fetchReservations()
    }

    Column {
        LocationBar(
            pickUpAt = "RSERVE AT",
            locationName = "Luar Cetate",
            pickUpTime = ""
        )

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(reservations) { reservation ->
                ItemCard(
                    title = reservation.title,
                    description = reservation.description,
                    imageRes = R.drawable.table,
                    buttonText = "Reserve Now",
                    backgroundColor = Color(0xFFF1F8E9),
                    textColor = Color.Black,
                ) {
                    navController.navigate(Screen.ReservationDetail.withSeats(reservation.seats))
                }
            }
        }
    }
}

data class ReservationItem(val title: String, val description: String, val seats: Int)