package com.poiasd.currencyapidbdemo.ui.convert

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.poiasd.currencyapidbdemo.R
import com.poiasd.currencyapidbdemo.databinding.FragmentConvertBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ConvertFragment : Fragment(R.layout.fragment_convert) {

    private val viewModel: ConvertViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentConvertBinding.bind(view)
        binding.apply {
            val currency = viewModel.currency

            textViewValueInRub.text = currency.valueInRub.toString()
            textViewDenominationInfo.text = "RUB = ${currency.denomination} ${currency.alpha3Code}"

            // Adjust result visibility.
            if (viewModel.isBeforeFirstConvert) {
                linearLayoutResult.visibility = View.INVISIBLE
            } else {
                linearLayoutResult.visibility = View.VISIBLE
            }

            buttonConvert.setOnClickListener {
                val input = editTextAmountRub.text.toString()
                textViewResultAmountRub.text = "$input RUB"
                textViewResultConverted.text = "${viewModel.convert(input.toDouble())} ${currency.alpha3Code}"

                if (linearLayoutResult.visibility == View.INVISIBLE) {
                    linearLayoutResult.visibility = View.VISIBLE
                }
            }
        }
    }
}
