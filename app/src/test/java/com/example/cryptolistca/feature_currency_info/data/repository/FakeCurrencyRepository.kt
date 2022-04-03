package com.example.cryptolistca.feature_currency_info.data.repository

import com.example.cryptolistca.feature_currency_info.domain.model.CurrencyInfo
import com.example.cryptolistca.feature_currency_info.domain.repository.CurrencyRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeCurrencyRepository : CurrencyRepository {

    private val currencyInfoList = mutableListOf<CurrencyInfo>()

    override suspend fun insertCurrencyInfoList() {
        currencyInfoList.clear()

        currencyInfoList.add(CurrencyInfo("BTC", "Bitcoin", "BTC"))
        currencyInfoList.add(CurrencyInfo("ETH", "Ethereum", "ETH"))
        currencyInfoList.add(CurrencyInfo("XRP", "XRP", "XRP"))
        currencyInfoList.add(CurrencyInfo("BCH", "Bitcoin Cash", "BCH"))
        currencyInfoList.add(CurrencyInfo("LTC", "Litecoin", "LTC"))
        currencyInfoList.add(CurrencyInfo("EOS", "EOS", "EOS"))
        currencyInfoList.add(CurrencyInfo("BNB", "Binance Coin", "BNB"))
        currencyInfoList.add(CurrencyInfo("LINK", "Chainlink", "LINK"))
        currencyInfoList.add(CurrencyInfo("NEO", "NEO", "NEO"))
        currencyInfoList.add(CurrencyInfo("ETC", "Ethereum Classic", "ETC"))
        currencyInfoList.add(CurrencyInfo("ONT", "Ontology", "ONT"))
        currencyInfoList.add(CurrencyInfo("CRO", "Crypto.com Chain", "CRO"))
        currencyInfoList.add(CurrencyInfo("CUC", "Cucumber", "CUC"))
        currencyInfoList.add(CurrencyInfo("USDC", "USD Coin", "USDC"))
    }

    override fun getCurrencyInfoList(): Flow<List<CurrencyInfo>> {
        return flow { emit(currencyInfoList) }
    }
}