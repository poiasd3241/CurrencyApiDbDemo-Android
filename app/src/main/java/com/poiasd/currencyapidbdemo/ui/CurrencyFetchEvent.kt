package com.poiasd.currencyapidbdemo.ui

import com.poiasd.currencyapidbdemo.data.model.Currency

/**
 * Container for currency fetching events.
 */
sealed class CurrencyFetchEvent {
    /**
     * Use for ongoing currency fetching.
     */
    object Fetching : CurrencyFetchEvent()

    /**
     * Container for a successful currency fetch result.
     *
     * @param currencies The list of fetched currencies.
     * @param successMessage The information about the fetched data (its origin, for example).
     */
    data class FetchSuccess(val currencies: List<Currency>, val successMessage: String) :
        CurrencyFetchEvent()

    /**
     * Container for the information about a failed fetch.
     *
     * @param failureMessage The information about the failed fetch (its origin, for example).
     */
    data class FetchFailure(val failureMessage: String) : CurrencyFetchEvent()
}
