package com.alexandrei.cafeapp.data.network.request

data class RegisterRequest(
    val name: String,
    val email: String,
    val password: String
)
