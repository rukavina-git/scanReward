package com.rukavina.scanreward

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.google.firebase.FirebaseApp
import com.google.firebase.perf.FirebasePerformance
import com.rukavina.scanreward.navigation.NavigationGraph
import com.rukavina.scanreward.ui.theme.ScanRewardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)
        setContent {
            val navController = rememberNavController()
            NavigationGraph(navController = navController)
        }
        //throw RuntimeException("Test Crash")

        val trace = FirebasePerformance.getInstance().newTrace("Log.d speed")
        trace.start()
        Log.d("Test", "testing performance")
        trace.stop()
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ScanRewardTheme {
        Greeting("Android")
    }
}