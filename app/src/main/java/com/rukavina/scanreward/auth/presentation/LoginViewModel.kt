package com.rukavina.scanreward.auth.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.rukavina.scanreward.auth.domain.LoginUseCase
import com.rukavina.scanreward.auth.domain.ResetPasswordUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class LoginState {
    object Idle : LoginState()
    object Success : LoginState()
    data class Error(val message: String) : LoginState()
}

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    var email by mutableStateOf("")
    var password by mutableStateOf("")

    private val _loginState = MutableStateFlow<LoginState>(LoginState.Idle)
    val loginState: StateFlow<LoginState> = _loginState

    fun login() {
        viewModelScope.launch {
            _loginState.value = LoginState.Idle

            val result = loginUseCase(email, password)

            _loginState.value = if (result.isSuccess) {
                LoginState.Success
            } else {
                val errorMessage = when (val exception = result.exceptionOrNull()) {
                    is FirebaseAuthInvalidCredentialsException -> "Incorrect email or password. Please try again."
                    is FirebaseAuthInvalidUserException -> "This email is not registered. Please check your email or register."
                    is FirebaseAuthUserCollisionException -> "This email is already registered. Try logging in."
                    else -> exception?.message ?: "An unknown error occurred. Please try again."
                }
                LoginState.Error(errorMessage)
            }
        }
    }
}