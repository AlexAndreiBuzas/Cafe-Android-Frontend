package com.alexandrei.cafeapp.domain.usecase.get

import com.alexandrei.cafeapp.data.network.response.order.CoffeeTypeResponse
import com.alexandrei.cafeapp.data.repository.CoffeeRepository
import javax.inject.Inject

class GetCoffeeTypesUseCase @Inject constructor(
    private val coffeeRepository: CoffeeRepository
) {
    suspend operator fun invoke(): Result<List<CoffeeTypeResponse>> {
        return coffeeRepository.getCoffeeTypes()
    }
}