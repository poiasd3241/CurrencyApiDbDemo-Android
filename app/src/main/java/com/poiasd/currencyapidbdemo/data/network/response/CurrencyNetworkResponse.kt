package com.poiasd.currencyapidbdemo.data.network.response

import com.google.gson.annotations.SerializedName

data class CurrencyNetworkResponse(
    @SerializedName("Valute")
    val currenciesList: CurrenciesList
)
