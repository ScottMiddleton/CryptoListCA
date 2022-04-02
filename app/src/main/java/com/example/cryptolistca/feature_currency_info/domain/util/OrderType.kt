package com.example.cryptolistca.feature_currency_info.domain.util

sealed class OrderType {
    object Unsorted: OrderType()
    object NameAscending: OrderType()
}
