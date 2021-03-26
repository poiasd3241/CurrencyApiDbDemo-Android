package com.poiasd.currencyapidbdemo.ui.convert

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.poiasd.currencyapidbdemo.data.model.Currency
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlin.math.round

@HiltViewModel
class ConvertViewModel @Inject constructor(
    private val state: SavedStateHandle
) : ViewModel() {

    private companion object {
        private const val KEY_CURRENCY = "currency"
        private const val KEY_IS_BEFORE_FIRST_CONVERT = "isBeforeFirstConvert"
    }

    val currency = state.get<Currency>(KEY_CURRENCY)!!

    // Use for adjusting UI visibility.
    var isBeforeFirstConvert = state.get<Boolean>(KEY_IS_BEFORE_FIRST_CONVERT) ?: true
        set(value) {
            field = value
            state.set(KEY_IS_BEFORE_FIRST_CONVERT, value)
        }

    fun convert(amountInRub: Double): Double {
        if (isBeforeFirstConvert) {
            isBeforeFirstConvert = false
        }
        // Round to 4 decimal places.
        return round(amountInRub * currency.denomination / currency.valueInRub * 10000) / 10000
    }
}
