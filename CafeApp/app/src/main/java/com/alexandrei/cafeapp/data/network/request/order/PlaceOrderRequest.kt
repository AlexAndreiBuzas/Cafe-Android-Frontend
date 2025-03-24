package com.alexandrei.cafeapp.data.network.request.order

data class PlaceOrderRequest(
    val coffeeType: String,
    val size: String,
    val customizations: List<String>
)