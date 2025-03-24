package com.alexandrei.cafeapp.ui.screens.myorders

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.alexandrei.cafeapp.ui.components.OrderCard
import com.alexandrei.cafeapp.ui.components.ReservationCard
import com.alexandrei.cafeapp.ui.screens.reservations.ReservationViewModel

@Composable
fun MyOrdersScreen(
    ordersViewModel: OrdersViewModel = hiltViewModel(),
    reservationViewModel: ReservationViewModel = hiltViewModel()
) {
    val orders by ordersViewModel.orders.collectAsState()
    val isLoading by ordersViewModel.isLoading.collectAsState()

    val reservations by reservationViewModel.reservations.collectAsState()

    LaunchedEffect(Unit) {
        ordersViewModel.fetchOrders()
        reservationViewModel.fetchReservations()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "My Orders",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        if (isLoading) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                if (orders.isEmpty() && reservations.isEmpty()) {
                    item {
                        Text("No orders or reservations found.", style = MaterialTheme.typography.bodyLarge)
                    }
                }

                if (orders.isNotEmpty()) {
                    item {
                        Text(
                            text = "Orders",
                            style = MaterialTheme.typography.titleLarge,
                            modifier = Modifier.padding(vertical = 8.dp)
                        )
                    }

                    items(orders) { order ->
                        OrderCard(order)
                    }
                }

                if (reservations.isNotEmpty()) {
                    item {
                        Text(
                            text = "Reservations",
                            style = MaterialTheme.typography.titleLarge,
                            modifier = Modifier.padding(vertical = 8.dp)
                        )
                    }

                    items(reservations) { reservation ->
                        ReservationCard(reservation)
                    }
                }
            }
        }
    }
}