package com.rocketseat.nlw.nearby.ui.screen.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.rocketseat.nlw.nearby.data.model.Market
import com.rocketseat.nlw.nearby.ui.screen.HomeScreen
import com.rocketseat.nlw.nearby.ui.screen.MarketDetailsScreen
import com.rocketseat.nlw.nearby.ui.screen.SplashScreen
import com.rocketseat.nlw.nearby.ui.screen.WelcomeScreen


@Composable
fun NearbyRoute(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Splash
    ){
        composable<Splash> {
            SplashScreen(
                onNavigateTo = {
                    navController.navigate(Welcome)
                }
            )
        }
        composable<Welcome> {
            WelcomeScreen(
                onNavigateTo = {
                    navController.navigate(Home)
                }
            )
        }
        composable<Home> {
            HomeScreen(
                onNavigateTo = { selectedMarket ->
                    navController.navigate(selectedMarket)
                }
            )
        }
        composable<Market> {
            val selectedMarket = it.toRoute<Market>()

            MarketDetailsScreen(
                market = selectedMarket,
                onNavigateTo = {
                    navController.popBackStack()
                }
            )
        }
    }

}