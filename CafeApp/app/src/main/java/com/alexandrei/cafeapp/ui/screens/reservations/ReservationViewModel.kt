package com.alexandrei.cafeapp.ui.screens.reservations

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexandrei.cafeapp.data.auth.TokenManager
import com.alexandrei.cafeapp.data.network.request.reservation.ReservationRequest
import com.alexandrei.cafeapp.data.network.response.reservation.ReservationResponse
import com.alexandrei.cafeapp.domain.usecase.create.CreateReservationUseCase
import com.alexandrei.cafeapp.domain.usecase.get.GetReservationsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReservationViewModel @Inject constructor(
    private val getReservationsUseCase: GetReservationsUseCase,
    private val createReservationUseCase: CreateReservationUseCase,
    private val authManager: TokenManager
) : ViewModel() {

    private val _reservations = MutableStateFlow<List<ReservationResponse>>(emptyList())
    val reservations: StateFlow<List<ReservationResponse>> = _reservations

    val reservationCreated = MutableStateFlow(false)
    val errorMessage = MutableStateFlow<String?>(null)

    fun fetchReservations() {
        viewModelScope.launch {
            val token = authManager.tokenFlow.first() ?: run {
                errorMessage.value = "Missing token"
                return@launch
            }

            val result = getReservationsUseCase(token)
            result.onSuccess { reservationList ->
                _reservations.value = reservationList
            }.onFailure {
                errorMessage.value = it.message
            }
        }
    }

    fun createReservation(dateTime: String) {
        viewModelScope.launch {
            val token = authManager.tokenFlow.first() ?: run {
                errorMessage.value = "Missing token"
                return@launch
            }

            val request = ReservationRequest(reservationTime = dateTime)
            val result = createReservationUseCase(request, token)

            result.onSuccess {
                reservationCreated.value = true
                fetchReservations()
            }.onFailure {
                errorMessage.value = it.message
            }
        }
    }
}