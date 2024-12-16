package com.rocketseat.nlw.nearby

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.navigation.compose.rememberNavController
import com.rocketseat.nlw.nearby.ui.navigation.NearbyRoute
import com.rocketseat.nlw.nearby.ui.screen.home.HomeViewModel
import com.rocketseat.nlw.nearby.ui.screen.market_details.MarketDetailsViewModel
import com.rocketseat.nlw.nearby.ui.theme.NearbyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NearbyTheme {
                val navController = rememberNavController()
                val homeViewModel by viewModels<HomeViewModel>()
                val marketDetailsViewModel by viewModels<MarketDetailsViewModel>()

                NearbyRoute(
                    homeViewModel = homeViewModel,
                    marketDetailsViewModel = marketDetailsViewModel,
                    navController = navController
                )
            }
        }
    }
}