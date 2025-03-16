package com.alexandrei.cafeapp.data.network.request

data class PlaceOrderRequest(
    val coffeeType: String,
    val size: String,
    val customizations: String? = null
)
