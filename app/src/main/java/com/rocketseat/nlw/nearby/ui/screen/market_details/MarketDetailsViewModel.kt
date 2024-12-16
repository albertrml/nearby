package com.rocketseat.nlw.nearby.ui.screen.market_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rocketseat.nlw.nearby.data.source.NearbyRemoteDataSource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MarketDetailsViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(MarketDetailsUiState())
    val uiState = _uiState.asStateFlow()

    fun onEvent(event: MarketDetailsUiEvent) {
        when (event) {
            is MarketDetailsUiEvent.OnFetchRules -> fetchRules(marketId = event.marketId)
            is MarketDetailsUiEvent.OnFetchCoupon -> fetchCoupon(qrCodeContent = event.qrCodeContent)
            is MarketDetailsUiEvent.OnResetCoupon -> resetCoupon()
        }
    }

    private fun fetchCoupon(qrCodeContent: String) {
        viewModelScope.launch {
            _uiState.update { currentUiState ->
                NearbyRemoteDataSource.patchCoupon(marketId = qrCodeContent).fold(
                    onSuccess = { coupon ->
                        currentUiState.copy(
                            coupon = coupon.code
                        )
                    },
                    onFailure = { _ ->
                        currentUiState.copy(
                            coupon = null
                        )
                    }
                )
            }
        }
    }

    private fun fetchRules(marketId: String) {
        viewModelScope.launch {
            _uiState.update { currentUiState ->
                NearbyRemoteDataSource.getMarketDetails(marketId = marketId).fold(
                    onSuccess = { marketDetails ->
                        currentUiState.copy(
                            rules = marketDetails.rules
                        )
                    },
                    onFailure = { _ ->
                        currentUiState.copy(
                            rules = emptyList()
                        )
                    }
                )
            }
        }
    }

    private fun resetCoupon() {
        _uiState.update { currentUiState ->
            currentUiState.copy(
                coupon = null
            )
        }
    }
}