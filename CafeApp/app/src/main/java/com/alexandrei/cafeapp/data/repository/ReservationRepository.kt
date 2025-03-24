package com.alexandrei.cafeapp.data.repository

import com.alexandrei.cafeapp.data.ApiService
import com.alexandrei.cafeapp.data.network.request.reservation.ReservationRequest
import com.alexandrei.cafeapp.data.network.response.reservation.ReservationResponse
import javax.inject.Inject

class ReservationRepository @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun getReservations(token: String): Result<List<ReservationResponse>> {
        return try {
            println("Making request to /api/reservations/my with token: $token")
            val response = apiService.getReservations("Bearer $token")
            if (response.isSuccessful) {
                response.body()?.let {
                    Result.success(it)
                } ?: Result.failure(Exception("Empty Response Body"))
            } else {
                val error = response.errorBody()?.string()
                println("Response error: $error")
                Result.failure(Exception("Failed to fetch reservations: $error"))
            }
        } catch (e: Exception) {
            println("Exception: ${e.message}")
            Result.failure(e)
        }
    }

    suspend fun createReservation(
        request: ReservationRequest,
        token: String
    ): Result<ReservationResponse> {
        return try {
            val response = apiService.createReservation("Bearer $token", request)
            if (response.isSuccessful) {
                response.body()?.let {
                    Result.success(it)
                } ?: Result.failure(Exception("Empty Response Body"))
            } else {
                Result.failure(Exception("Failed to create reservation: ${response.errorBody()?.string()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}