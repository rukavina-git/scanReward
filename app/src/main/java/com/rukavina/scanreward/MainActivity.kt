package com.rukavina.scanreward

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.google.firebase.FirebaseApp
import com.rukavina.scanreward.navigation.BottomNavigationBar
import com.rukavina.scanreward.navigation.NavigationGraph
import com.rukavina.scanreward.ui.theme.ScanRewardTheme
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)

        val isAuthenticated = FirebaseAuth.getInstance().currentUser != null

        setContent {
            ScanRewardTheme {
                val navController = rememberNavController()

                Scaffold(
                    bottomBar = {
                        if (isAuthenticated) {
                            BottomNavigationBar(navController = navController)
                        }
                    }
                ) { innerPadding ->
                    NavigationGraph(
                        navController = navController,
                        isAuthenticated = isAuthenticated,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}