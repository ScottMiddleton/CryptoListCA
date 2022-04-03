package com.example.cryptolistca.feature_currency_info.data.repository

import com.example.cryptolistca.feature_currency_info.data.data_source.CurrencyInfoDao
import com.example.cryptolistca.feature_currency_info.domain.model.CurrencyInfo
import com.example.cryptolistca.feature_currency_info.domain.repository.CurrencyRepository
import kotlinx.coroutines.flow.Flow

class CurrencyRepositoryImpl(
    private val dao: CurrencyInfoDao
): CurrencyRepository {

    override suspend fun insertCurrencyInfoList(currencyInfoList: List<CurrencyInfo>) {
        dao.insertCurrencyInfoList(currencyInfoList)
    }

    override fun getCurrencyInfoList(): Flow<List<CurrencyInfo>> {
        return dao.getCurrencyInfoList()
    }
}