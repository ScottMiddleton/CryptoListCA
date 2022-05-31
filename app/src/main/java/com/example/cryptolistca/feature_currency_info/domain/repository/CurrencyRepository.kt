package com.example.cryptolistca.feature_currency_info.domain.repository

import com.example.cryptolistca.feature_currency_info.domain.model.CurrencyInfo
import kotlinx.coroutines.flow.Flow

interface CurrencyRepository {

    suspend fun insertCurrencyInfoList(currencyInfoList: List<CurrencyInfo>)

    fun getCurrencyInfoList(): Flow<List<CurrencyInfo>>
}