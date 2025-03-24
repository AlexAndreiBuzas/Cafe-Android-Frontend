package com.alexandrei.cafeapp.ui.screens.myorders

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexandrei.cafeapp.data.auth.TokenManager
import com.alexandrei.cafeapp.data.network.response.order.OrderResponse
import com.alexandrei.cafeapp.data.repository.OrderRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrdersViewModel @Inject constructor(
    private val orderRepository: OrderRepository,
    private val authManager: TokenManager
) : ViewModel() {

    private val _orders = MutableStateFlow<List<OrderResponse>>(emptyList())
    val orders: StateFlow<List<OrderResponse>> = _orders

    val isLoading = MutableStateFlow(false)

    fun fetchOrders() {
        viewModelScope.launch {
            isLoading.value = true
            val token = authManager.tokenFlow.first() ?: return@launch
            orderRepository.getMyOrders(token).onSuccess {
                _orders.value = it
            }
            isLoading.value = false
        }
    }
}