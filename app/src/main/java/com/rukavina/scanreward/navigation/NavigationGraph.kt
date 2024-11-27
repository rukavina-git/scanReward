package com.rukavina.scanreward.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.rukavina.scanreward.auth.presentation.LoginScreen
import com.rukavina.scanreward.auth.presentation.RegisterScreen
import com.rukavina.scanreward.auth.presentation.ResetPasswordScreen
import com.rukavina.scanreward.ui.home.HomeScreen
import com.rukavina.scanreward.ui.journey.JourneyScreen
import com.rukavina.scanreward.ui.rewards.RewardsScreen
import com.rukavina.scanreward.ui.profile.ProfileScreen

@Composable
fun NavigationGraph(
    navController: NavHostController,
    isAuthenticated: Boolean,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = if (isAuthenticated) Screen.Home.route else Screen.Login.route,
        modifier = modifier
    ) {
        // Auth Screens
        composable(Screen.Login.route) {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Login.route) {
                            inclusive = true
                        } // Clear login screen from stack
                    }
                },
                onNavigateToRegister = {
                    navController.navigate(Screen.Register.route)
                },
                onResetPassword = {
                    navController.navigate(Screen.ResetPassword.route)
                }
            )
        }

        composable(Screen.Register.route) {
            RegisterScreen(
                onRegisterSuccess = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Register.route) {
                            inclusive = true
                        } // Clear register screen from stack
                    }
                },
                onNavigateToLogin = {
                    navController.popBackStack()
                }
            )
        }

        composable(Screen.ResetPassword.route) {
            ResetPasswordScreen(
                onResetSuccess = {
                    navController.popBackStack()  // Pop current screen from stack
                },
                onCancel = {
                    navController.popBackStack()  // Cancel and pop back
                }
            )
        }

        // Main Screens
        composable(Screen.Home.route) {
            HomeScreen(navController)
        }
        composable(Screen.Journey.route) {
            JourneyScreen(navController)
        }
        composable(Screen.Rewards.route) {
            RewardsScreen(navController)
        }
        composable(Screen.Profile.route) {
            ProfileScreen(navController)
        }
    }
}

sealed class Screen(val route: String) {
    // Auth Screens
    data object Login : Screen("login")
    data object Register : Screen("register")
    data object ResetPassword : Screen("resetPassword")

    // Main App Screens
    data object Home : Screen("home")
    data object Journey : Screen("journey")
    data object Rewards : Screen("rewards")
    data object Profile : Screen("profile")
}