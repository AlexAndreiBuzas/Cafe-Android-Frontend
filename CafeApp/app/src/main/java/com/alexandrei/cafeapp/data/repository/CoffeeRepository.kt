package com.alexandrei.cafeapp.data.repository

import com.alexandrei.cafeapp.data.ApiService
import com.alexandrei.cafeapp.data.network.response.order.CoffeeTypeResponse
import com.alexandrei.cafeapp.data.network.response.order.CustomizationOption
import javax.inject.Inject

class CoffeeRepository @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun getCoffeeTypes(): Result<List<CoffeeTypeResponse>> {
        return try {
            val response = apiService.getCoffeeTypes()
            if (response.isSuccessful) {
                response.body()?.let {
                    Result.success(it)
                } ?: Result.failure(Exception("Empty Response Body"))
            } else {
                Result.failure(Exception("Failed to fetch coffee types: ${response.errorBody()?.string()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getCustomizations(): Result<List<CustomizationOption>> {
        return try {
            val response = apiService.getCustomizations()
            if (response.isSuccessful) {
                response.body()?.let {
                    Result.success(it)
                } ?: Result.failure(Exception("Empty Customizations Response"))
            } else {
                Result.failure(Exception("Failed to fetch customizations: ${response.errorBody()?.string()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}