package com.alexandrei.cafeapp.domain.usecase.create

import com.alexandrei.cafeapp.data.network.request.order.PlaceOrderRequest
import com.alexandrei.cafeapp.data.network.response.order.OrderResponse
import com.alexandrei.cafeapp.data.repository.OrderRepository
import javax.inject.Inject

class PlaceOrderUseCase @Inject constructor(
    private val orderRepository: OrderRepository
) {
    suspend operator fun invoke(request: PlaceOrderRequest, token: String): Result<OrderResponse> {
        return orderRepository.placeOrder(request, token)
    }
}