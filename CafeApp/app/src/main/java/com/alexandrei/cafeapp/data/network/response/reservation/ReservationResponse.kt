package com.alexandrei.cafeapp.data.network.response.reservation

data class ReservationResponse(
    val id: Int,
    val userId: Int,
    val reservationTime: String,
    val createdAt: String,
    val status: String
)
