package com.rukavina.scanreward.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.rukavina.scanreward.ui.home.HomeScreen
import com.rukavina.scanreward.ui.scan.ScanScreen
import com.rukavina.scanreward.ui.rewards.RewardsScreen

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(Screen.Home.route) {
            HomeScreen(navController)
        }
        composable(Screen.Scan.route) {
            ScanScreen(navController)
        }
        composable(Screen.Rewards.route) {
            RewardsScreen(navController)
        }
    }
}

sealed class Screen(val route: String) {
    data object Home : Screen("home")
    data object Scan : Screen("scan")
    data object Rewards : Screen("rewards")
}