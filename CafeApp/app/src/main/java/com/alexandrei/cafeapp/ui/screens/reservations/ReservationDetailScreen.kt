package com.alexandrei.cafeapp.ui.screens.reservations

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.alexandrei.cafeapp.ui.components.TimePickerDialog
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@Composable
fun ReservationDetailScreen(
    seats: Int,
    navController: NavController,
    viewModel: ReservationViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")

    var pickedTime by remember { mutableStateOf<LocalTime?>(null) }
    var showTimePicker by remember { mutableStateOf(false) }

    val todayDate = LocalDate.now()
    val reservationCreated by viewModel.reservationCreated.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Reserve Table for $seats",
            style = MaterialTheme.typography.headlineSmall,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedButton(onClick = { showTimePicker = true }) {
            Text(text = pickedTime?.toString() ?: "Pick a time")
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                val fullDateTime = "${todayDate}T${pickedTime ?: return@Button}"
                viewModel.createReservation(fullDateTime)
            },
            enabled = pickedTime != null,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Confirm Reservation")
        }

        if (reservationCreated) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Reservation placed successfully!",
                color = MaterialTheme.colorScheme.primary
            )
        }

        errorMessage?.let {
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = it, color = MaterialTheme.colorScheme.error)
        }
    }

    if (showTimePicker) {
        TimePickerDialog(
            onDismissRequest = { showTimePicker = false },
            onTimeConfirm = { pickedTime = it; showTimePicker = false }
        )
    }
}