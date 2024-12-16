package com.rocketseat.nlw.nearby.ui.screen.market_details

import com.rocketseat.nlw.nearby.data.model.MarketRule

data class MarketDetailsUiState(
    val rules: List<MarketRule>? = null,
    val coupon: String? = null
)