package com.rukavina.scanreward.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.rukavina.scanreward.R
import com.rukavina.scanreward.ui.theme.ScanRewardTheme

@Composable
fun HomeScreen(navController: NavController) {
    // Hardcoded test data
    val userName = "John Wayne"
    val points = 45
    val nextRewardName = "Free Coffee"
    val nextRewardPoints = 100
    val nextRewardImage = R.drawable.ic_launcher_foreground

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Welcome $userName",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Text(
            text = "Points: $points",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        Text(
            text = "Next Reward",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(8.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Image(
                    painter = painterResource(id = nextRewardImage),
                    contentDescription = null,
                    modifier = Modifier
                        .size(60.dp)
                        .padding(end = 16.dp)
                )

                Column(
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(text = nextRewardName, style = MaterialTheme.typography.bodyLarge)
                    Text(text = "$nextRewardPoints points", style = MaterialTheme.typography.bodySmall)
                }
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = { /* Handle scan action */ },
            shape = CircleShape,
            modifier = Modifier
                .size(80.dp)
                .align(Alignment.CenterHorizontally),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = "Scan",
                modifier = Modifier.size(40.dp),
                tint = Color.White
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    val navController = rememberNavController()
    ScanRewardTheme {
        HomeScreen(navController = navController)
    }
}