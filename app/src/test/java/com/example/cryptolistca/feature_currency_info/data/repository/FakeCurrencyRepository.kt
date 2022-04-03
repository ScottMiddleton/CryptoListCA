package com.example.cryptolistca.feature_currency_info.data.repository

import com.example.cryptolistca.feature_currency_info.domain.model.CurrencyInfo
import com.example.cryptolistca.feature_currency_info.domain.repository.CurrencyRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeCurrencyRepository : CurrencyRepository {

    private var currencyInfoList = listOf<CurrencyInfo>()

    override suspend fun insertCurrencyInfoList(currencyInfoList: List<CurrencyInfo>) {
        this.currencyInfoList = currencyInfoList
    }

    override fun getCurrencyInfoList(): Flow<List<CurrencyInfo>> {
        return flow { emit(currencyInfoList) }
    }
}