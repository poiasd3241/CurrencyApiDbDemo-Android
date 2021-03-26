package com.poiasd.currencyapidbdemo.ui.fetch

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.poiasd.currencyapidbdemo.data.model.Currency
import com.poiasd.currencyapidbdemo.databinding.ItemCurrencyBinding

class CurrenciesAdapter(
    private val currenciesListener: CurrenciesListener
) : ListAdapter<Currency, CurrenciesAdapter.CurrenciesViewHolder>(DiffCallback()) {

    class CurrenciesViewHolder(
        private val binding: ItemCurrencyBinding,
        private val currenciesListener: CurrenciesListener
    ) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        init {
            itemView.setOnClickListener(this)
        }

        /**
         * Sets up item UI.
         */
        fun bind(currency: Currency) {
            binding.apply {
                textViewAlpha3Code.text = currency.alpha3Code
                textViewName.text = currency.nameInRussian
                textViewValueInRub.text = currency.valueInRub.toString()
                textViewDenominationInfo.text = "RUB = ${currency.denomination} ${currency.alpha3Code}"
            }
        }

        override fun onClick(v: View?) {
            currenciesListener.onCurrencyItemClick(bindingAdapterPosition)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrenciesViewHolder {
        val binding = ItemCurrencyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CurrenciesViewHolder(binding, currenciesListener)
    }

    override fun onBindViewHolder(holder: CurrenciesViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }

    /**
     * Defines the behavior for comparing items for [CurrenciesAdapter].
     */
    class DiffCallback : DiffUtil.ItemCallback<Currency>() {
        override fun areItemsTheSame(oldItem: Currency, newItem: Currency) =
            oldItem.alpha3Code == newItem.alpha3Code

        override fun areContentsTheSame(oldItem: Currency, newItem: Currency) =
            oldItem == newItem
    }

    /**
     * Use for interactions with items of [CurrenciesAdapter].
     */
    interface CurrenciesListener {

        /**
         * Handle the click on the item of [CurrenciesAdapter].
         *
         * @param position The position of the clicked item.
         */
        fun onCurrencyItemClick(position: Int)
    }
}
