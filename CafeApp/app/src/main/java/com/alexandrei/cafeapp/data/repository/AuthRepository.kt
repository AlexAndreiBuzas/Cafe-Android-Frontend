package com.alexandrei.cafeapp.data.repository

import com.alexandrei.cafeapp.data.ApiService
import com.alexandrei.cafeapp.data.network.request.auth.LoginRequest
import com.alexandrei.cafeapp.data.network.request.auth.RegisterRequest
import com.alexandrei.cafeapp.data.network.response.auth.AuthResponse
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val apiService: ApiService
) {

    suspend fun login(email: String, password: String): Result<AuthResponse> {
        return try {
            val response = apiService.login(LoginRequest(email, password))

            if (response.isSuccessful) {
                response.body()?.let {
                    Result.success(it)
                } ?: Result.failure(Exception("Empty Response Body"))
            } else {
                Result.failure(Exception("Login failed: ${response.errorBody()?.string() ?: "Unknown error"}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun register(name: String, email: String, password: String): Result<AuthResponse> {
        return try {
            val response = apiService.register(RegisterRequest(name, email, password))

            if (response.isSuccessful) {
                response.body()?.let {
                    Result.success(it)
                } ?: Result.failure(Exception("Empty Response Body"))
            } else {
                Result.failure(Exception("Registration failed: ${response.errorBody()?.string() ?: "Unknown error"}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}