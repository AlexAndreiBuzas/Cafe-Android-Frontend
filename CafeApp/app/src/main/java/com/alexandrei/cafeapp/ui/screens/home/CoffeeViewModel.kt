package com.alexandrei.cafeapp.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexandrei.cafeapp.data.network.response.order.CoffeeTypeResponse
import com.alexandrei.cafeapp.domain.usecase.get.GetCoffeeTypesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoffeeViewModel @Inject constructor(
    private val getCoffeeTypesUseCase: GetCoffeeTypesUseCase
) : ViewModel() {

    private val _coffeeTypes = MutableStateFlow<List<CoffeeTypeResponse>>(emptyList())
    val coffeeTypes: StateFlow<List<CoffeeTypeResponse>> = _coffeeTypes

    fun fetchCoffeeTypes() {
        viewModelScope.launch {
            val result = getCoffeeTypesUseCase()
            result.onSuccess { coffeeList ->
                _coffeeTypes.value = coffeeList
            }
        }
    }
}