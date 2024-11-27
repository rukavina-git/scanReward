package com.rukavina.scanreward.auth.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rukavina.scanreward.auth.domain.ResetPasswordUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class ResetPasswordState {
    data object Idle : ResetPasswordState()
    data object Success : ResetPasswordState()
    data class Error(val message: String) : ResetPasswordState()
}

@HiltViewModel
class ResetPasswordViewModel @Inject constructor(
    private val resetPasswordUseCase: ResetPasswordUseCase
) : ViewModel() {

    var email = ""

    private val _resetState = MutableStateFlow<ResetPasswordState>(ResetPasswordState.Idle)
    val resetState: StateFlow<ResetPasswordState> = _resetState

    fun resetPassword() {
        viewModelScope.launch {
            _resetState.value = ResetPasswordState.Idle

            val result = resetPasswordUseCase(email)

            _resetState.value = if (result.isSuccess) {
                ResetPasswordState.Success
            } else {
                ResetPasswordState.Error(result.exceptionOrNull()?.message ?: "An unknown error occurred.")
            }
        }
    }
}