package com.alexandrei.cafeapp.ui.screens.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexandrei.cafeapp.data.auth.TokenManager
import com.alexandrei.cafeapp.domain.usecase.auth.LoginUseCase
import com.alexandrei.cafeapp.domain.model.AuthState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val authManager: TokenManager
) : ViewModel() {

    private val _authState = MutableStateFlow<AuthState>(AuthState.Idle)
    val authState: StateFlow<AuthState> = _authState

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _authState.value = AuthState.Loading
            try {
                val result = loginUseCase(email, password)
                result.onSuccess { response ->
                    authManager.saveToken(response.token)
                    _authState.value = AuthState.Success(response)
                }.onFailure {
                    _authState.value = AuthState.Error(it.message ?: "Unknown error")
                }
            } catch (e: Exception) {
                _authState.value = AuthState.Error(e.localizedMessage ?: "Unknown error")
            }
        }
    }
}