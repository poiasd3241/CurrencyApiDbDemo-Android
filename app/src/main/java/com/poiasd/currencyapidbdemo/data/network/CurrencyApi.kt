package com.poiasd.currencyapidbdemo.data.network

import com.poiasd.currencyapidbdemo.data.network.response.CurrencyNetworkResponse
import retrofit2.Response
import retrofit2.http.GET

interface CurrencyApi {

    @GET("daily_json.js")
    suspend fun getCurrencies(): Response<CurrencyNetworkResponse>
}
