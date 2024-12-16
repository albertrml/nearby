package com.rocketseat.nlw.nearby.ui.component.market_details

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rocketseat.nlw.nearby.R
import com.rocketseat.nlw.nearby.data.model.Market
import com.rocketseat.nlw.nearby.data.mock.mockMarkets
import com.rocketseat.nlw.nearby.ui.theme.Gray400
import com.rocketseat.nlw.nearby.ui.theme.Gray500
import com.rocketseat.nlw.nearby.ui.theme.Typography

@Composable
fun NearbyMarketDetailsInfos(
    modifier: Modifier = Modifier,
    market: Market
){
    Column (
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Informações",
            style = Typography.headlineSmall,
            color = Gray400
        )
        Column(
            modifier = modifier,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            market.apply {
                InfoMarket(
                    icon = R.drawable.ic_ticket,
                    content = "$coupons cupons disponíveis",
                    description = "Ícone de cupons disponíveis"
                )
                InfoMarket(
                    icon = R.drawable.ic_map_pin,
                    content = address,
                    description = "Ícone de Endereço"
                )
                InfoMarket(
                    icon = R.drawable.ic_phone,
                    content = phone,
                    description = "Ícone de telefone"
                )
            }
        }
    }
}

@Composable
fun InfoMarket(
    modifier: Modifier = Modifier,
    @DrawableRes icon: Int,
    content: String,
    description: String? = null
){
    Row (
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = icon),
            tint = Gray500,
            contentDescription = description
        )
        Text(
            text = content,
            style = Typography.labelMedium,
            color = Gray500
        )
    }
}

@Preview
@Composable
private fun NearbyMarketDetailsInfosPreview() {
    NearbyMarketDetailsInfos(
        market = mockMarkets.first(),
        modifier = Modifier.fillMaxWidth()
    )
}