package com.example.cryptolistca.feature_currency_info.data.repository

import com.example.cryptolistca.feature_currency_info.data.local.entity.CurrencyInfoEntity
import com.example.cryptolistca.feature_currency_info.domain.repository.CurrencyRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeCurrencyRepository : CurrencyRepository {

    private var currencyInfoList = listOf<CurrencyInfoEntity>()

    override suspend fun insertCurrencyInfoList(currencyInfoList: List<CurrencyInfoEntity>) {
        this.currencyInfoList = currencyInfoList
    }

    override fun getCurrencyInfoList(): Flow<List<CurrencyInfoEntity>> {
        return flow { emit(currencyInfoList) }
    }
}