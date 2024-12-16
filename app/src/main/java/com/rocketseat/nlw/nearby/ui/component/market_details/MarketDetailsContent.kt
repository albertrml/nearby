package com.rocketseat.nlw.nearby.ui.component.market_details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rocketseat.nlw.nearby.data.model.Market
import com.rocketseat.nlw.nearby.data.mock.mockCoupons
import com.rocketseat.nlw.nearby.data.mock.mockMarkets
import com.rocketseat.nlw.nearby.data.model.MarketRule
import com.rocketseat.nlw.nearby.ui.theme.Typography

@Composable
fun MarketDetailsContent(
    modifier: Modifier = Modifier,
    market: Market,
    rules: List<MarketRule>? = emptyList(),
    coupons: List<String>? = emptyList()
) {
    Column {
        Text(
            text = market.name,
            style = Typography.headlineLarge
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = market.description,
            style = Typography.bodyLarge
        )
        Spacer(modifier = Modifier.height(48.dp))

        Column(
            modifier = modifier.verticalScroll(rememberScrollState())
        ){
            NearbyMarketDetailsInfos(market = market)
            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 24.dp)
            )
            if (!rules.isNullOrEmpty()) {
                NearbyMarketDetailsRules(rules = rules)
                HorizontalDivider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 24.dp)
                )
            }
            if (!coupons.isNullOrEmpty()) {
                NearbyMarketDetailsCoupons(coupons = coupons)
            }
        }
    }
}

@Preview
@Composable
private fun MarketDetailsContentPreview() {
    MarketDetailsContent(
        market = mockMarkets.first()
    )
}