package com.alexandrei.cafeapp.domain.usecase.get

import com.alexandrei.cafeapp.data.repository.CoffeeRepository
import javax.inject.Inject

class GetCustomizationsUseCase @Inject constructor(
    private val coffeeRepository: CoffeeRepository
) {
    suspend operator fun invoke() = coffeeRepository.getCustomizations()
}