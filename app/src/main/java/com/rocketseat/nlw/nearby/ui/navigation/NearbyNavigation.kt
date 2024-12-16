package com.rocketseat.nlw.nearby.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.rocketseat.nlw.nearby.data.model.Market
import com.rocketseat.nlw.nearby.ui.screen.home.HomeScreen
import com.rocketseat.nlw.nearby.ui.screen.home.HomeViewModel
import com.rocketseat.nlw.nearby.ui.screen.market_details.MarketDetailsScreen
import com.rocketseat.nlw.nearby.ui.screen.market_details.MarketDetailsUiEvent
import com.rocketseat.nlw.nearby.ui.screen.market_details.MarketDetailsViewModel
import com.rocketseat.nlw.nearby.ui.screen.qrcode.QRCodeScannerScreen
import com.rocketseat.nlw.nearby.ui.screen.splash.SplashScreen
import com.rocketseat.nlw.nearby.ui.screen.welcome.WelcomeScreen


@Composable
fun NearbyRoute(
    homeViewModel: HomeViewModel,
    navController: NavHostController,
    marketDetailsViewModel: MarketDetailsViewModel
) {

    val homeUiState by homeViewModel.uiState.collectAsStateWithLifecycle()
    val marketDetailsUiState by marketDetailsViewModel.uiState.collectAsStateWithLifecycle()

    NavHost(
        navController = navController,
        startDestination = Splash
    ) {
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
                },
                uiState = homeUiState,
                onEvent = homeViewModel::onEvent
            )
        }
        composable<Market> {
            val selectedMarket = it.toRoute<Market>()

            MarketDetailsScreen(
                market = selectedMarket,
                uiState = marketDetailsUiState,
                onEvent = marketDetailsViewModel::onEvent,
                onNavigateTo = {
                    navController.popBackStack()
                },
                onNavigateToQRCodeScanner = {
                    navController.navigate(QRCodeScanner)
                }
            )
        }

        composable<QRCodeScanner> {
            QRCodeScannerScreen(
                onCompletedScan =  { qrCodeContent: String ->
                    if(qrCodeContent.isNotEmpty()){
                        marketDetailsViewModel.onEvent(
                            MarketDetailsUiEvent.OnFetchCoupon(qrCodeContent)
                        )
                        navController.popBackStack()
                    }
                }
            )
        }
    }

}