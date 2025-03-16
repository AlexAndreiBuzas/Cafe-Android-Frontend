package com.alexandrei.cafeapp.ui.screens.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexandrei.cafeapp.domain.usecase.LoginUseCase
import com.alexandrei.cafeapp.domain.model.AuthState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    private val _authState = MutableStateFlow<AuthState>(AuthState.Idle)
    val authState: StateFlow<AuthState> = _authState

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _authState.value = AuthState.Loading
            try {
                val result = loginUseCase(email, password)
                _authState.value = result.fold(
                    onSuccess = { AuthState.Success(it) },
                    onFailure = { AuthState.Error(it.localizedMessage ?: "Login failed") }
                )
            } catch (e: Exception) {
                _authState.value = AuthState.Error(e.localizedMessage ?: "Unexpected error")
            }
        }
    }
}