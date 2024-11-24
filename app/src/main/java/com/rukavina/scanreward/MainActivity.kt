package com.rukavina.scanreward

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.google.firebase.FirebaseApp
import com.google.firebase.perf.FirebasePerformance
import com.rukavina.scanreward.navigation.BottomNavigationBar
import com.rukavina.scanreward.navigation.NavigationGraph
import com.rukavina.scanreward.ui.theme.ScanRewardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)
        setContent {
            ScanRewardTheme {
                val navController = rememberNavController()

                Scaffold(
                    bottomBar = { BottomNavigationBar(navController = navController) }
                ) { innerPadding ->
                    NavigationGraph(
                        navController = navController,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }

        val trace = FirebasePerformance.getInstance().newTrace("Log.d speed")
        trace.start()
        Log.d("Test", "testing performance")
        trace.stop()
    }
}