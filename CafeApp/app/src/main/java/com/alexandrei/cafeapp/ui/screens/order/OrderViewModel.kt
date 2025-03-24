package com.alexandrei.cafeapp.ui.screens.order

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexandrei.cafeapp.data.auth.TokenManager
import com.alexandrei.cafeapp.data.network.response.order.CustomizationOption
import com.alexandrei.cafeapp.data.network.response.order.CoffeeTypeResponse
import com.alexandrei.cafeapp.domain.usecase.get.GetCoffeeTypesUseCase
import com.alexandrei.cafeapp.data.network.request.order.PlaceOrderRequest
import com.alexandrei.cafeapp.domain.model.OrderUiState
import com.alexandrei.cafeapp.domain.usecase.get.GetCustomizationsUseCase
import com.alexandrei.cafeapp.domain.usecase.create.PlaceOrderUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrderViewModel @Inject constructor(
    private val getCoffeeTypesUseCase: GetCoffeeTypesUseCase,
    private val getCustomizationsUseCase: GetCustomizationsUseCase,
    private val placeOrderUseCase: PlaceOrderUseCase,
    private val authManager: TokenManager
) : ViewModel() {

    private val _uiState = MutableStateFlow(OrderUiState())
    val uiState: StateFlow<OrderUiState> = _uiState

    private var allCoffees: List<CoffeeTypeResponse> = emptyList()

    fun setSelectedCoffee(coffeeId: String) {
        val coffee = allCoffees.find { it.id.toString() == coffeeId }
        _uiState.value = _uiState.value.copy(selectedCoffee = coffee)
    }

    fun fetchCoffees() {
        viewModelScope.launch {
            val result = getCoffeeTypesUseCase()
            result.onSuccess { list ->
                allCoffees = list
                _uiState.value = _uiState.value.copy(coffeeList = list)
            }
        }
    }

    fun loadCustomizations() {
        viewModelScope.launch {
            val result = getCustomizationsUseCase()
            result.onSuccess { options ->
                _uiState.value = _uiState.value.copy(customizations = options)
            }.onFailure {
                _uiState.value = _uiState.value.copy(error = it.message)
            }
        }
    }

    fun placeOrder(coffeeType: String, size: String, customizations: List<CustomizationOption>) {
        val customizationValues = customizations.map { it.option }

        val request = PlaceOrderRequest(
            coffeeType = coffeeType,
            size = size,
            customizations = customizationValues
        )

        viewModelScope.launch {
            val token = authManager.tokenFlow.firstOrNull() ?: run {
                return@launch
            }

            placeOrderUseCase(request, token)
                .onSuccess {
                    _uiState.value = _uiState.value.copy(orderPlaced = true)
                }
                .onFailure {
                    _uiState.value = _uiState.value.copy(error = it.message)
                }
        }
    }

    fun resetOrderPlaced() {
        _uiState.value = _uiState.value.copy(orderPlaced = false)
    }
}