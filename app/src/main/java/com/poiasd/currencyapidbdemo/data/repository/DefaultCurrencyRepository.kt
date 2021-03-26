package com.poiasd.currencyapidbdemo.data.repository

import com.poiasd.currencyapidbdemo.data.db.CurrencyDao
import com.poiasd.currencyapidbdemo.data.db.CurrencyDbEntity
import com.poiasd.currencyapidbdemo.data.db.CurrencyDbEntityMapper
import com.poiasd.currencyapidbdemo.data.model.Currency
import com.poiasd.currencyapidbdemo.data.network.CurrencyApi
import com.poiasd.currencyapidbdemo.data.network.CurrencyNetworkEntityMapper
import com.poiasd.currencyapidbdemo.util.DataState
import javax.inject.Inject

class DefaultCurrencyRepository @Inject constructor(
    private val currencyDao: CurrencyDao,
    private val currencyApi: CurrencyApi,
    private val currencyDbEntityMapper: CurrencyDbEntityMapper,
    private val currencyNetworkEntityMapper: CurrencyNetworkEntityMapper
) : CurrencyRepository {

    override suspend fun getCurrencies(): DataState<List<Currency>> {
        // If DB has data, get from DB; otherwise, get from API.
        return if (currencyDao.isCurrencyTableEmpty()) {
            getCurrenciesFromApi()
        } else {
            getCurrenciesFromDb()
        }
    }

    override suspend fun getCurrenciesFromDb(): DataState<List<Currency>> {
        return try {
            val dbCurrencies = currencyDao.getCurrencies()
            val currencies = currencyDbEntityMapper.mapFromEntityList(dbCurrencies)
            return DataState.Success(currencies, "Loaded from database!")
        } catch (e: Exception) {
            DataState.Error(e.message ?: "An error occurred while fetching currencies from database.")
        }
    }

    override suspend fun getCurrenciesFromApi(): DataState<List<Currency>> {
        return try {
            val response = currencyApi.getCurrencies()
            val result = response.body()
            return if (response.isSuccessful && result != null) {
                val currenciesDomain = mutableListOf<Currency>()
                val currenciesNetworkPacked = result.currenciesList
                currenciesNetworkPacked.apply {
                    currenciesDomain.add(currencyNetworkEntityMapper.mapFromEntity(aMD))
                    currenciesDomain.add(currencyNetworkEntityMapper.mapFromEntity(aUD))
                    currenciesDomain.add(currencyNetworkEntityMapper.mapFromEntity(aZN))
                    currenciesDomain.add(currencyNetworkEntityMapper.mapFromEntity(bGN))
                    currenciesDomain.add(currencyNetworkEntityMapper.mapFromEntity(bRL))
                    currenciesDomain.add(currencyNetworkEntityMapper.mapFromEntity(bYN))
                    currenciesDomain.add(currencyNetworkEntityMapper.mapFromEntity(cAD))
                    currenciesDomain.add(currencyNetworkEntityMapper.mapFromEntity(cHF))
                    currenciesDomain.add(currencyNetworkEntityMapper.mapFromEntity(cNY))
                    currenciesDomain.add(currencyNetworkEntityMapper.mapFromEntity(cZK))
                    currenciesDomain.add(currencyNetworkEntityMapper.mapFromEntity(dKK))
                    currenciesDomain.add(currencyNetworkEntityMapper.mapFromEntity(eUR))
                    currenciesDomain.add(currencyNetworkEntityMapper.mapFromEntity(gBP))
                    currenciesDomain.add(currencyNetworkEntityMapper.mapFromEntity(hKD))
                    currenciesDomain.add(currencyNetworkEntityMapper.mapFromEntity(hUF))
                    currenciesDomain.add(currencyNetworkEntityMapper.mapFromEntity(iNR))
                    currenciesDomain.add(currencyNetworkEntityMapper.mapFromEntity(jPY))
                    currenciesDomain.add(currencyNetworkEntityMapper.mapFromEntity(kGS))
                    currenciesDomain.add(currencyNetworkEntityMapper.mapFromEntity(kRW))
                    currenciesDomain.add(currencyNetworkEntityMapper.mapFromEntity(kZT))
                    currenciesDomain.add(currencyNetworkEntityMapper.mapFromEntity(mDL))
                    currenciesDomain.add(currencyNetworkEntityMapper.mapFromEntity(nOK))
                    currenciesDomain.add(currencyNetworkEntityMapper.mapFromEntity(pLN))
                    currenciesDomain.add(currencyNetworkEntityMapper.mapFromEntity(rON))
                    currenciesDomain.add(currencyNetworkEntityMapper.mapFromEntity(sEK))
                    currenciesDomain.add(currencyNetworkEntityMapper.mapFromEntity(sGD))
                    currenciesDomain.add(currencyNetworkEntityMapper.mapFromEntity(tJS))
                    currenciesDomain.add(currencyNetworkEntityMapper.mapFromEntity(tMT))
                    currenciesDomain.add(currencyNetworkEntityMapper.mapFromEntity(tRY))
                    currenciesDomain.add(currencyNetworkEntityMapper.mapFromEntity(uAH))
                    currenciesDomain.add(currencyNetworkEntityMapper.mapFromEntity(uSD))
                    currenciesDomain.add(currencyNetworkEntityMapper.mapFromEntity(uZS))
                    currenciesDomain.add(currencyNetworkEntityMapper.mapFromEntity(xDR))
                    currenciesDomain.add(currencyNetworkEntityMapper.mapFromEntity(zAR))
                }
                // Update the database.
                currencyDao.upsert(currencyDbEntityMapper.mapToEntityList(currenciesDomain))

                DataState.Success(currenciesDomain, "Fetched from API!")
            } else {
                DataState.Error(response.message())
            }
        } catch (e: Exception) {
            DataState.Error(e.message ?: "An error occurred while fetching currencies from api.")
        }
    }
}
