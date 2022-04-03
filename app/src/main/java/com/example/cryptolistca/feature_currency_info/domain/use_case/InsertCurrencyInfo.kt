package com.example.cryptolistca.feature_currency_info.domain.use_case

import com.example.cryptolistca.feature_currency_info.domain.model.CurrencyInfo
import com.example.cryptolistca.feature_currency_info.domain.repository.CurrencyRepository

class InsertCurrencyInfo(
    private val repository: CurrencyRepository
) {
    suspend operator fun invoke(currencyInfoList: List<CurrencyInfo>) {
        repository.insertCurrencyInfoList(currencyInfoList)
    }
}