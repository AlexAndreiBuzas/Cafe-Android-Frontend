package com.alexandrei.cafeapp.domain.usecase

import com.alexandrei.cafeapp.data.network.response.AuthResponse
import com.alexandrei.cafeapp.data.repository.AuthRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor (
    private val authRepository: AuthRepository
) {

    suspend operator fun invoke(email: String, password: String): Result<AuthResponse> {
        return authRepository.login(email, password)
    }
}