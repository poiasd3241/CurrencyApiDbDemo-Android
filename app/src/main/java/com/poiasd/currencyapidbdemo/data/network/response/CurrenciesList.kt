package com.poiasd.currencyapidbdemo.data.network.response

import com.google.gson.annotations.SerializedName

data class CurrenciesList(
    @SerializedName("AMD")
    val aMD: CurrencyNetworkEntity,
    @SerializedName("AUD")
    val aUD: CurrencyNetworkEntity,
    @SerializedName("AZN")
    val aZN: CurrencyNetworkEntity,
    @SerializedName("BGN")
    val bGN: CurrencyNetworkEntity,
    @SerializedName("BRL")
    val bRL: CurrencyNetworkEntity,
    @SerializedName("BYN")
    val bYN: CurrencyNetworkEntity,
    @SerializedName("CAD")
    val cAD: CurrencyNetworkEntity,
    @SerializedName("CHF")
    val cHF: CurrencyNetworkEntity,
    @SerializedName("CNY")
    val cNY: CurrencyNetworkEntity,
    @SerializedName("CZK")
    val cZK: CurrencyNetworkEntity,
    @SerializedName("DKK")
    val dKK: CurrencyNetworkEntity,
    @SerializedName("EUR")
    val eUR: CurrencyNetworkEntity,
    @SerializedName("GBP")
    val gBP: CurrencyNetworkEntity,
    @SerializedName("HKD")
    val hKD: CurrencyNetworkEntity,
    @SerializedName("HUF")
    val hUF: CurrencyNetworkEntity,
    @SerializedName("INR")
    val iNR: CurrencyNetworkEntity,
    @SerializedName("JPY")
    val jPY: CurrencyNetworkEntity,
    @SerializedName("KGS")
    val kGS: CurrencyNetworkEntity,
    @SerializedName("KRW")
    val kRW: CurrencyNetworkEntity,
    @SerializedName("KZT")
    val kZT: CurrencyNetworkEntity,
    @SerializedName("MDL")
    val mDL: CurrencyNetworkEntity,
    @SerializedName("NOK")
    val nOK: CurrencyNetworkEntity,
    @SerializedName("PLN")
    val pLN: CurrencyNetworkEntity,
    @SerializedName("RON")
    val rON: CurrencyNetworkEntity,
    @SerializedName("SEK")
    val sEK: CurrencyNetworkEntity,
    @SerializedName("SGD")
    val sGD: CurrencyNetworkEntity,
    @SerializedName("TJS")
    val tJS: CurrencyNetworkEntity,
    @SerializedName("TMT")
    val tMT: CurrencyNetworkEntity,
    @SerializedName("TRY")
    val tRY: CurrencyNetworkEntity,
    @SerializedName("UAH")
    val uAH: CurrencyNetworkEntity,
    @SerializedName("USD")
    val uSD: CurrencyNetworkEntity,
    @SerializedName("UZS")
    val uZS: CurrencyNetworkEntity,
    @SerializedName("XDR")
    val xDR: CurrencyNetworkEntity,
    @SerializedName("ZAR")
    val zAR: CurrencyNetworkEntity
)
