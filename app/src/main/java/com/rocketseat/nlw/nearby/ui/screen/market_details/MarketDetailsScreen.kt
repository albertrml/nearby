package com.rocketseat.nlw.nearby.ui.screen.market_details

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.rocketseat.nlw.nearby.R
import com.rocketseat.nlw.nearby.data.model.Market
import com.rocketseat.nlw.nearby.data.mock.mockMarkets
import com.rocketseat.nlw.nearby.ui.component.button.NearbyButton
import com.rocketseat.nlw.nearby.ui.component.market_details.MarketDetailsContent

@Composable
fun MarketDetailsScreen(
    modifier: Modifier = Modifier,
    market: Market,
    uiState: MarketDetailsUiState,
    onEvent: (MarketDetailsUiEvent) -> Unit,
    onNavigateTo: () -> Unit,
    onNavigateToQRCodeScanner: () -> Unit
) {

    LaunchedEffect(true) {
        onEvent(MarketDetailsUiEvent.OnFetchRules(marketId = market.id))
    }

    Log.d("HomeScreen", "MarketDetailsScreen: ${uiState.coupon}")

    Box(
        modifier = modifier.fillMaxSize()
    ) {
        NearbyButton(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(start = 8.dp, top = 24.dp),
            iconRes = R.drawable.ic_arrow_left,
            onClick = onNavigateTo
        )
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.3f),
            contentDescription = "Imagem do Local",
            contentScale = ContentScale.Crop,
            model = market.coverUrl
        )
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
                .fillMaxWidth()
                .fillMaxHeight(0.75f)
                .align(Alignment.BottomCenter)
                .background(Color.White)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(36.dp)
            ) {
                Column(
                    modifier = Modifier
                        .weight(1f)
                ) {
                    MarketDetailsContent(
                        market = market,
                        rules = uiState.rules,
                        coupons = if(!uiState.coupon.isNullOrEmpty())
                            listOf(uiState.coupon) else emptyList()
                    )
                }

                NearbyButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 24.dp),
                    text = "Ler QR Code",
                    onClick = onNavigateToQRCodeScanner
                )
            }
        }
    }
}

@Preview
@Composable
private fun MarketDetailsScreenPreview() {
    MarketDetailsScreen(
        market = mockMarkets.first(),
        onNavigateTo = {},
        uiState = MarketDetailsUiState(),
        onEvent = {},
        onNavigateToQRCodeScanner = {}
    )
}