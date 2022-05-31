package com.example.cryptolistca.feature_currency_info.data.repository

import com.example.cryptolistca.feature_currency_info.data.local.CurrencyInfoDao
import com.example.cryptolistca.feature_currency_info.data.local.entity.CurrencyInfoEntity
import com.example.cryptolistca.feature_currency_info.data.mapper.toCurrencyInfo
import com.example.cryptolistca.feature_currency_info.data.mapper.toCurrencyInfoEntity
import com.example.cryptolistca.feature_currency_info.domain.model.CurrencyInfo
import com.example.cryptolistca.feature_currency_info.domain.repository.CurrencyRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CurrencyRepositoryImpl(
    private val dao: CurrencyInfoDao
) : CurrencyRepository {

    override suspend fun insertCurrencyInfoList(currencyInfoList: List<CurrencyInfo>) {
        dao.insertCurrencyInfoList(currencyInfoList.map { it.toCurrencyInfoEntity() })
    }

    override fun getCurrencyInfoList(): Flow<List<CurrencyInfo>> {
        return dao.getCurrencyInfoList().map { entities -> entities.map { it.toCurrencyInfo() } }
    }
}