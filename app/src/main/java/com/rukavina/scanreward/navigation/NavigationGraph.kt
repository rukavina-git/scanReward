package com.rukavina.scanreward.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.rukavina.scanreward.ui.home.HomeScreen
import com.rukavina.scanreward.ui.journey.JourneyScreen
import com.rukavina.scanreward.ui.rewards.RewardsScreen
import com.rukavina.scanreward.ui.profile.ProfileScreen

@Composable
fun NavigationGraph(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
        modifier = modifier
    ) {
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
    data object Home : Screen("home")
    data object Journey : Screen("journey")
    data object Rewards : Screen("rewards")
    data object Profile : Screen("profile")
}