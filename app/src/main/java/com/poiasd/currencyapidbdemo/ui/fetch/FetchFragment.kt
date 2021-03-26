package com.poiasd.currencyapidbdemo.ui.fetch

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.poiasd.currencyapidbdemo.R
import com.poiasd.currencyapidbdemo.databinding.FragmentFetchBinding
import com.poiasd.currencyapidbdemo.ui.CurrencyFetchEvent
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

@AndroidEntryPoint
class FetchFragment : Fragment(R.layout.fragment_fetch), CurrenciesAdapter.CurrenciesListener {

    private val viewModel: FetchViewModel by viewModels()
    private lateinit var currenciesAdapter: CurrenciesAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentFetchBinding.bind(view)
        val context = requireContext()
        currenciesAdapter = CurrenciesAdapter(this)
        // Keep scroll position after configuration changes.
        currenciesAdapter.stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY

        binding.apply {
            recyclerViewCurrencies.apply {
                adapter = currenciesAdapter
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
            }
            buttonUpdate.setOnClickListener {
                viewModel.fetchCurrencies(true)
            }
        }

        fun adjustControlsVisibility(isFetching: Boolean, isSuccess: Boolean?) {
            binding.apply {
                if (isFetching) {
                    linearLayoutUpdating.visibility = View.VISIBLE
                    linearLayoutInfo.visibility = View.INVISIBLE
                    buttonUpdate.visibility = View.INVISIBLE
                    recyclerViewCurrencies.visibility = View.INVISIBLE
                } else {
                    linearLayoutUpdating.visibility = View.INVISIBLE
                    linearLayoutInfo.visibility = View.VISIBLE
                    buttonUpdate.visibility = View.VISIBLE
                    if (isSuccess!!) {
                        recyclerViewCurrencies.visibility = View.VISIBLE
                        textViewCurrencyTapTip.visibility = View.VISIBLE
                    }
                    else{
                        textViewCurrencyTapTip.visibility = View.INVISIBLE
                    }
                }
            }
        }

        lifecycleScope.launchWhenStarted {
            viewModel.fetchEvent.collect { event ->
                when (event) {
                    is CurrencyFetchEvent.Fetching -> adjustControlsVisibility(isFetching = true, isSuccess = null)
                    is CurrencyFetchEvent.FetchFailure -> {
                        adjustControlsVisibility(isFetching = false, isSuccess = false)
                        binding.textViewInfo.text = event.failureMessage
                    }
                    is CurrencyFetchEvent.FetchSuccess -> {
                        adjustControlsVisibility(isFetching = false, isSuccess = true)
                        binding.textViewInfo.text = event.successMessage
                        currenciesAdapter.submitList(event.currencies.sortedBy { currency -> currency.alpha3Code })
                    }
                }
            }
        }

        if (viewModel.isBeforeFirstFetch) {
            // Perform initial fetch.
            viewModel.fetchCurrencies(false)
        }
    }

    /**
     * Navigates to ConvertFragment based on the clicked currency item inside the [currenciesAdapter].
     *
     * @param position The position of the clicked item.
     */
    override fun onCurrencyItemClick(position: Int) {
        val currency = currenciesAdapter.currentList[position]
        val action = FetchFragmentDirections.actionFetchFragmentToConvertFragment(
            currency,
            "Convert RUB to ${currency.alpha3Code}"
        )
        findNavController().navigate(action)
    }
}
