package com.rukavina.scanreward.auth.data

interface AuthRepository {
    suspend fun loginUser(email: String, password: String): Result<Unit>
    suspend fun registerUser(email: String, password: String): Result<Unit>
}