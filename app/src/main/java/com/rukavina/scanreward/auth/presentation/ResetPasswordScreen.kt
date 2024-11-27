package com.rukavina.scanreward.auth.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import android.util.Log

@Composable
fun ResetPasswordScreen(
    onResetSuccess: () -> Unit,
    onCancel: () -> Unit,
    viewModel: ResetPasswordViewModel = hiltViewModel()
) {
    val resetState by viewModel.resetState.collectAsState()
    var email by remember { mutableStateOf(viewModel.email) }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Input Field
            TextField(
                value = email,
                onValueChange = {
                    email = it
                    viewModel.email = it
                },
                label = { Text("Enter your email") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Send Reset Email Button
            Button(
                onClick = { viewModel.resetPassword() },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Send Reset Email")
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Cancel Button
            Button(
                onClick = onCancel,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Cancel")
            }
        }

        when (resetState) {
            is ResetPasswordState.Success -> {
                LaunchedEffect(Unit) {
                    Log.d("ResetPassword", "Reset email sent successfully!")
                    onResetSuccess()
                }
            }

            is ResetPasswordState.Error -> {
                val errorMessage = (resetState as ResetPasswordState.Error).message
                LaunchedEffect(errorMessage) {
                    Log.e("ResetPassword", "Error: $errorMessage")
                }
            }

            else -> {} // Idle or Loading state
        }
    }
}