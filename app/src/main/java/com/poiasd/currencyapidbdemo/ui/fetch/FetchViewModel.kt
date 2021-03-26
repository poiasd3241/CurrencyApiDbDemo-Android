package com.poiasd.currencyapidbdemo.ui.fetch

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.poiasd.currencyapidbdemo.data.repository.CurrencyRepository
import com.poiasd.currencyapidbdemo.ui.CurrencyFetchEvent
import com.poiasd.currencyapidbdemo.util.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class FetchViewModel @Inject constructor(
    private val currencyRepository: CurrencyRepository
) : ViewModel() {

    private val _event = MutableStateFlow<CurrencyFetchEvent>(CurrencyFetchEvent.Fetching)
    val fetchEvent: StateFlow<CurrencyFetchEvent> = _event

    // Use for adjusting UI visibility.
    var isBeforeFirstFetch = true

    /**
     * Fetches currencies from repository.
     *
     * @param isUpdate `true` if the currencies must be updated from the internet; otherwise, `false`.
     */
    fun fetchCurrencies(isUpdate: Boolean) {
        if (isBeforeFirstFetch) {
            isBeforeFirstFetch = false
        }
        viewModelScope.launch(Dispatchers.IO) {
            _event.value = CurrencyFetchEvent.Fetching
            val result = when (isUpdate) {
                true -> currencyRepository.getCurrenciesFromApi()
                false -> currencyRepository.getCurrencies()
            }
            when (result) {
                is DataState.Error -> _event.value =
                    CurrencyFetchEvent.FetchFailure(getMessageWithTimestamp(result.errorMessage))
                is DataState.Success -> _event.value =
                    CurrencyFetchEvent.FetchSuccess(result.data,getMessageWithTimestamp(result.message))
            }
        }
    }

    private fun getMessageWithTimestamp(message: String): String {
        return "${ZonedDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"))} - $message"
    }
}
