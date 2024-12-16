package com.rocketseat.nlw.nearby.ui.component.market


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.rocketseat.nlw.nearby.R
import com.rocketseat.nlw.nearby.data.mock.mockMarkets
import com.rocketseat.nlw.nearby.data.model.Market
import com.rocketseat.nlw.nearby.ui.theme.Gray100
import com.rocketseat.nlw.nearby.ui.theme.Gray200
import com.rocketseat.nlw.nearby.ui.theme.Gray400
import com.rocketseat.nlw.nearby.ui.theme.RedBase
import com.rocketseat.nlw.nearby.ui.theme.Typography

@Composable
fun NearbyMarketCard(
    modifier: Modifier,
    market: Market,
    onClick: (Market) -> Unit
) {
    Card(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .background(Gray100)
            .border(
                width = 1.dp,
                color = Gray200,
                shape = RoundedCornerShape(12.dp)
            ),
        onClick = {
            onClick(market)
        }
    ) {
        // TODO: Substituir pela imagem em market.coverUrl
        ContentMarketCard(
            iconUrl = market.coverUrl,
            nameMarket = market.name,
            descriptionMarket = market.description,
            couponsMarket = market.coupons
        )
    }
}

@Composable
fun ContentMarketCard(
    iconUrl: String,
    nameMarket: String,
    descriptionMarket: String,
    couponsMarket: Int,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Gray100)
            .padding(16.dp),
        verticalAlignment = CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        AsyncImage(
            modifier = Modifier
                .clip(RoundedCornerShape(12.dp))
                .fillMaxWidth(0.3f)
                .height(IntrinsicSize.Min)
                .aspectRatio(
                    ratio = 1f,
                    matchHeightConstraintsFirst = true
                ),
            contentScale = ContentScale.Crop,
            model = iconUrl,
            contentDescription = "Imagem do estabelecimento"
        )

        Column {
            Text(
                text = nameMarket,
                style = Typography.headlineSmall
                    .copy(fontSize = 14.sp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = descriptionMarket,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                style = Typography.bodyLarge
                    .copy(fontSize = 12.sp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                verticalAlignment = CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(
                    modifier = Modifier.size(24.dp),
                    tint = if (couponsMarket > 0) RedBase else Gray400,
                    painter = painterResource(id = R.drawable.ic_ticket),
                    contentDescription = "Ícone de Cupom"
                )
                Text(
                    text = "$couponsMarket cupons disponíveis",
                    color = Gray400,
                    style = Typography.bodyMedium.copy(fontSize = 12.sp)
                )
            }
        }
    }

}

@Preview
@Composable
private fun NearbyMarketCardPreview() {
    NearbyMarketCard(
        modifier = Modifier.fillMaxWidth(),
        market = mockMarkets.last(),
        onClick = {}
    )
}