package com.rukavina.scanreward.auth.domain

import com.rukavina.scanreward.auth.data.AuthRepository
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(email: String, password: String): Result<Unit> {
        return authRepository.registerUser(email, password)
    }
}