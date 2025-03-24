package com.alexandrei.cafeapp.domain.model;

import com.alexandrei.cafeapp.data.network.response.auth.AuthResponse

sealed class AuthState {
    data object Idle : AuthState()
    data object Loading : AuthState()
    data class Success(val authResponse: AuthResponse) : AuthState()
    data class Error(val message: String) : AuthState()
}
