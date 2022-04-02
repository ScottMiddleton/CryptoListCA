package com.example.crytpolistca.feature_currency_info.domain.use_case

import com.example.crytpolistca.feature_currency_info.domain.repository.CurrencyRepository

class InsertCurrencyInfo(
    private val repository: CurrencyRepository
) {
    suspend operator fun invoke(){
        repository.insertCurrencyInfoList()
    }
}