package com.rukavina.scanreward.auth.domain

import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class ResetPasswordUseCase @Inject constructor(
    private val auth: FirebaseAuth
) {
    suspend operator fun invoke(email: String): Result<Unit> {
        return try {
            auth.sendPasswordResetEmail(email).await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}