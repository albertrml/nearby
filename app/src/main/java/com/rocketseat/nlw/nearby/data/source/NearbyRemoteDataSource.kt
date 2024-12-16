package com.rocketseat.nlw.nearby.data.source

import com.rocketseat.nlw.nearby.core.network.KtorHttpClient.httpClientAndroid
import com.rocketseat.nlw.nearby.data.model.Category
import com.rocketseat.nlw.nearby.data.model.Coupon
import com.rocketseat.nlw.nearby.data.model.Market
import com.rocketseat.nlw.nearby.data.model.MarketDetails
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.patch

object NearbyRemoteDataSource {

    private const val LOCAL_HOST_EMULATOR_BASE_URL = "http://192.168.0.3:3333"
    private const val BASE_URL = LOCAL_HOST_EMULATOR_BASE_URL
    private val client = httpClientAndroid

    // 1 - Busca de categorias
    suspend fun getCategories(): Result<List<Category>> = try {
        val url = "$BASE_URL/categories"
        val categories = client.get(url)
            .body<List<Category>>()
        Result.success(categories)
    } catch (e: Exception) {
        Result.failure(e)
    }

    // 2 - Busca de Locais (com base em uma categoria)
    suspend fun getMarkets(categoryId: String): Result<List<Market>> = try {
        val url = "$BASE_URL/markets/category/$categoryId"
        val markets = client.get(url)
            .body<List<Market>>()
        Result.success(markets)
    } catch (e: Exception) {
        Result.failure(e)
    }

    // 3 - Busca de detalhes de um local (com base em um local específico)
    suspend fun getMarketDetails(marketId: String): Result<MarketDetails> = try {
        val url = "$BASE_URL/markets/$marketId"
        val marketDetails = client.get(url)
            .body<MarketDetails>()
        Result.success(marketDetails)
    } catch (e: Exception) {
        Result.failure(e)
    }

    // 4 - Gerar cupom a partir de leitura do qrcode
    suspend fun patchCoupon(marketId: String): Result<Coupon> = try {
        val url = "$BASE_URL/coupons/$marketId"
        val coupon = httpClientAndroid.patch(url)
            .body<Coupon>()
        Result.success(coupon)
    } catch (e: Exception) {
        Result.failure(e)
    }

}