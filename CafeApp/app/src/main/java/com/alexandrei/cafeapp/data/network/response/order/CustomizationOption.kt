package com.alexandrei.cafeapp.data.network.response.order

data class CustomizationOption(
    val id: Int,
    val category: String,
    val option: String,
    val additionalCost: Double
)
