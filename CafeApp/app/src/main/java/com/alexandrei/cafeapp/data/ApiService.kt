package com.alexandrei.cafeapp.data

import com.alexandrei.cafeapp.data.network.request.LoginRequest
import com.alexandrei.cafeapp.data.network.request.PlaceOrderRequest
import com.alexandrei.cafeapp.data.network.request.RegisterRequest
import com.alexandrei.cafeapp.data.network.response.AuthResponse
import com.alexandrei.cafeapp.data.network.response.OrderResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {
    @POST("/api/auth/register")
    suspend fun register(@Body request: RegisterRequest): Response<AuthResponse>

    @POST("/api/auth/login")
    suspend fun login(@Body request: LoginRequest): Response<AuthResponse>

    @GET("/api/orders/my-orders")
    suspend fun getOrders(@Header("Authorization") token: String): Response<List<OrderResponse>>

    @POST("/api/orders")
    suspend fun placeOrder(
        @Header("Authorization") token: String,
        @Body request: PlaceOrderRequest
    ): Response<OrderResponse>
}