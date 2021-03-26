package com.poiasd.currencyapidbdemo.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Currency domain model.
 */
@Parcelize
data class Currency(
    val alpha3Code: String,
    val nameInRussian: String,
    val denomination: Int,
    val valueInRub: Double
) : Parcelable
