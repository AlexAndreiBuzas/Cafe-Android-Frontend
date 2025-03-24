package com.alexandrei.cafeapp.domain.usecase.auth

import com.alexandrei.cafeapp.data.network.response.auth.AuthResponse
import com.alexandrei.cafeapp.data.repository.AuthRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor (
    private val authRepository: AuthRepository
) {

    suspend operator fun invoke(email: String, password: String): Result<AuthResponse> {
        return authRepository.login(email, password)
    }
}