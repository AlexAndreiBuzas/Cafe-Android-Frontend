package com.alexandrei.cafeapp.data.network.response.order

data class OrderResponse(
    val id: Int,
    val coffeeType: String,
    val size: String,
    val customizations: String?,
    val status: String,
    val pickupCode: String
)