package com.rukavina.scanreward.auth.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rukavina.scanreward.auth.domain.RegisterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase
) : ViewModel() {

    // Define the state for registration (Success/Error)
    private val _registrationState = MutableStateFlow<RegisterState>(RegisterState.Idle)
    val registrationState: StateFlow<RegisterState> = _registrationState

    fun register(email: String, password: String, confirmPassword: String) {
        // Basic validation for email and password
        if (email.isEmpty() || password.isEmpty()) {
            _registrationState.value = RegisterState.Error("Email and password cannot be empty")
            return
        }

        if (password != confirmPassword) {
            _registrationState.value = RegisterState.Error("Passwords do not match")
            return
        }

        viewModelScope.launch {
            val result = registerUseCase(email, password)

            // Checking for success and failure
            if (result.isSuccess) {
                _registrationState.value = RegisterState.Success
            } else {
                val errorMessage = result.exceptionOrNull()?.message ?: "Registration failed"
                _registrationState.value = RegisterState.Error(errorMessage)
            }
        }
    }
}

sealed class RegisterState {
    data object Idle : RegisterState()
    data object Success : RegisterState()
    data class Error(val message: String) : RegisterState()
}
