package com.example.cryptolistca.feature_currency_info.presentation.currency_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptolistca.databinding.ListItemCurrencyInfoBinding
import com.example.cryptolistca.feature_currency_info.domain.model.CurrencyInfo

class CurrencyListAdapter :
    androidx.recyclerview.widget.ListAdapter<CurrencyInfo, CurrencyListAdapter.ViewHolder>(
        TestResultsDiffCallback()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class ViewHolder private constructor(private val binding: ListItemCurrencyInfoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(currencyInfo: CurrencyInfo) {
            binding.currencyInfoItem = currencyInfo
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemCurrencyInfoBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }

    }

    class TestResultsDiffCallback : DiffUtil.ItemCallback<CurrencyInfo>() {
        override fun areItemsTheSame(oldItem: CurrencyInfo, newItem: CurrencyInfo): Boolean {
            return oldItem == newItem
        }


        override fun areContentsTheSame(oldItem: CurrencyInfo, newItem: CurrencyInfo): Boolean {
            return oldItem == newItem
        }
    }
}
