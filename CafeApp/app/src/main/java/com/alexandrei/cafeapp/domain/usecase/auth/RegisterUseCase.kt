package com.alexandrei.cafeapp.domain.usecase.auth

import com.alexandrei.cafeapp.data.network.response.auth.AuthResponse
import com.alexandrei.cafeapp.data.repository.AuthRepository
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {

    suspend operator fun invoke(name: String, email: String, password: String): Result<AuthResponse> {
        return authRepository.register(name, email, password)
    }
}