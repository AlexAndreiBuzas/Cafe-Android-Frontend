package com.alexandrei.cafeapp.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.alexandrei.cafeapp.data.network.response.reservation.ReservationResponse

@Composable
fun ReservationCard(reservation: ReservationResponse) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF1F8E9)),
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Reservation", style = MaterialTheme.typography.titleMedium)
            Text("Time: ${reservation.reservationTime}")
            Text("Status: ${reservation.status}")
        }
    }
}