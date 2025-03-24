package com.alexandrei.cafeapp.domain.model

import com.alexandrei.cafeapp.data.network.response.order.CoffeeTypeResponse
import com.alexandrei.cafeapp.data.network.response.order.CustomizationOption

data class OrderUiState(
    val coffeeList: List<CoffeeTypeResponse> = emptyList(),
    val selectedCoffee: CoffeeTypeResponse? = null,
    val customizations: List<CustomizationOption> = emptyList(),
    val orderPlaced: Boolean = false,
    val error: String? = null
)