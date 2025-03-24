package com.alexandrei.cafeapp.domain.usecase.get

import com.alexandrei.cafeapp.data.network.response.reservation.ReservationResponse
import com.alexandrei.cafeapp.data.repository.ReservationRepository
import javax.inject.Inject

class GetReservationsUseCase @Inject constructor(
    private val reservationRepository: ReservationRepository
) {
    suspend operator fun invoke(token: String): Result<List<ReservationResponse>> {
        return reservationRepository.getReservations(token)
    }
}