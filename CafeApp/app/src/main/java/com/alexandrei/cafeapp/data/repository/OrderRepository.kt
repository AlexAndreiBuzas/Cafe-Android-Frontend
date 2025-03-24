package com.alexandrei.cafeapp.data.repository

import com.alexandrei.cafeapp.data.ApiService
import com.alexandrei.cafeapp.data.auth.TokenManager
import com.alexandrei.cafeapp.data.network.request.order.PlaceOrderRequest
import com.alexandrei.cafeapp.data.network.response.order.OrderResponse
import javax.inject.Inject

class OrderRepository @Inject constructor(
    private val apiService: ApiService,
    private val authManager: TokenManager
) {
    suspend fun placeOrder(request: PlaceOrderRequest, token: String): Result<OrderResponse> {
        return try {
            val response = apiService.placeOrder("Bearer $token", request)
            if (response.isSuccessful) {
                response.body()?.let { Result.success(it) }
                    ?: Result.failure(Exception("Empty body"))
            } else {
                Result.failure(Exception("Error: ${response.errorBody()?.string()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getMyOrders(token: String): Result<List<OrderResponse>> {
        return try {
            val response = apiService.getOrders("Bearer $token")
            if (response.isSuccessful) {
                response.body()?.let { Result.success(it) }
                    ?: Result.failure(Exception("Empty response body"))
            } else {
                Result.failure(Exception("Failed to fetch orders: ${response.errorBody()?.string()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}