package com.poiasd.currencyapidbdemo.data.repository

import com.poiasd.currencyapidbdemo.data.model.Currency
import com.poiasd.currencyapidbdemo.util.DataState

/**
 * Main repository interface.
 */
interface CurrencyRepository {

    /**
     * Use for fetching currencies from database or API.
     */
    suspend fun getCurrencies(): DataState<List<Currency>>

    /**
     * Use for fetching currencies from database.
     */
    suspend fun getCurrenciesFromDb(): DataState<List<Currency>>

    /**
     * Use for fetching currencies from API.
     */
    suspend fun getCurrenciesFromApi(): DataState<List<Currency>>
}
