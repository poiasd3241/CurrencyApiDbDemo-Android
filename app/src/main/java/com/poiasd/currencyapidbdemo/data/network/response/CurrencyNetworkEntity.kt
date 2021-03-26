package com.poiasd.currencyapidbdemo.data.network.response

import com.google.gson.annotations.SerializedName

data class CurrencyNetworkEntity(
    @SerializedName("CharCode")
    val alpha3Code: String,
    @SerializedName("Name")
    val nameInRussian: String,
    @SerializedName("Nominal")
    val denomination: Int,
    @SerializedName("Value")
    val valueInRub: Double
)
