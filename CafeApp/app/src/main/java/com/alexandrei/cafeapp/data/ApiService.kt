package com.alexandrei.cafeapp.data

import com.alexandrei.cafeapp.data.network.request.auth.LoginRequest
import com.alexandrei.cafeapp.data.network.request.order.PlaceOrderRequest
import com.alexandrei.cafeapp.data.network.request.auth.RegisterRequest
import com.alexandrei.cafeapp.data.network.request.reservation.ReservationRequest
import com.alexandrei.cafeapp.data.network.response.order.CustomizationOption
import com.alexandrei.cafeapp.data.network.response.auth.AuthResponse
import com.alexandrei.cafeapp.data.network.response.order.CoffeeTypeResponse
import com.alexandrei.cafeapp.data.network.response.order.OrderResponse
import com.alexandrei.cafeapp.data.network.response.reservation.ReservationResponse
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

    @GET("/api/coffee-options/types")
    suspend fun getCoffeeTypes(): Response<List<CoffeeTypeResponse>>

    @GET("/api/coffee-options/customizations")
    suspend fun getCustomizations(): Response<List<CustomizationOption>>

    @POST("/api/reservations")
    suspend fun createReservation(
        @Header("Authorization") token: String,
        @Body request: ReservationRequest
    ): Response<ReservationResponse>

    @GET("/api/reservations/my")
    suspend fun getReservations(
        @Header("Authorization") token: String
    ): Response<List<ReservationResponse>>
}