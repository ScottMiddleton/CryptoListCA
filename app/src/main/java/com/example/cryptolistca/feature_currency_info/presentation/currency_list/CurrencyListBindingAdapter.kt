package com.example.cryptolistca.feature_currency_info.presentation.currency_list

import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("initial")
fun TextView.initialTv(name: String) {
    text = name.first().toString()
}

@BindingAdapter("name")
fun TextView.nameTv(name: String) {
    text = name
}

@BindingAdapter("symbol")
fun TextView.symbolTv(symbol: String) {
    text = symbol
}