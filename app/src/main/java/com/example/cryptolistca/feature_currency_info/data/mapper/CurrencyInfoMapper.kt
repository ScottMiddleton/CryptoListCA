package com.example.cryptolistca.feature_currency_info.data.mapper

import com.example.cryptolistca.feature_currency_info.data.local.entity.CurrencyInfoEntity
import com.example.cryptolistca.feature_currency_info.domain.model.CurrencyInfo

fun CurrencyInfoEntity.toCurrencyInfo(): CurrencyInfo {
    return CurrencyInfo(
        id = id,
        name = name,
        symbol = symbol
    )
}