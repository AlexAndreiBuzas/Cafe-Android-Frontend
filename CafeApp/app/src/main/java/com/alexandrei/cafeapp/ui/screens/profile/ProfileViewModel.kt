package com.alexandrei.cafeapp.ui.screens.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexandrei.cafeapp.data.auth.TokenManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val authManager: TokenManager
) : ViewModel() {

    private val _username = MutableStateFlow<String?>(null)
    val username: StateFlow<String?> = _username

    init {
        viewModelScope.launch {
            authManager.tokenFlow.collectLatest { token ->
                token?.let {
                    _username.value = authManager.getUsernameFromToken(it)
                }
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            authManager.clearToken()
        }
    }
}