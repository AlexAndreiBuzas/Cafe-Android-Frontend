package com.alexandrei.cafeapp.domain.usecase.create

import com.alexandrei.cafeapp.data.network.request.reservation.ReservationRequest
import com.alexandrei.cafeapp.data.network.response.reservation.ReservationResponse
import com.alexandrei.cafeapp.data.repository.ReservationRepository
import javax.inject.Inject

class CreateReservationUseCase @Inject constructor(
    private val reservationRepository: ReservationRepository
) {
    suspend operator fun invoke(request: ReservationRequest, token: String): Result<ReservationResponse> {
        return reservationRepository.createReservation(request, token)
    }
}