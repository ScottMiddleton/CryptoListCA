package com.example.cryptolistca.feature_currency_info.domain.use_case

import com.example.cryptolistca.feature_currency_info.data.repository.FakeCurrencyRepository
import com.example.cryptolistca.feature_currency_info.domain.model.CurrencyInfo
import com.example.cryptolistca.feature_currency_info.domain.util.OrderType
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetCurrencyInfoTest {

    private lateinit var getCurrencyInfoUseCase: GetCurrencyInfo
    private lateinit var fakeRepository: FakeCurrencyRepository

    @Before
    fun setUp() {
        fakeRepository = FakeCurrencyRepository()
        getCurrencyInfoUseCase = GetCurrencyInfo(fakeRepository)

        runBlocking {
            val currencyInfoList = mutableListOf<CurrencyInfo>()

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

            fakeRepository.insertCurrencyInfoList(currencyInfoList)
        }
    }

    @Test
    fun `Order notes by unsorted, correct order`() = runBlocking {
        val currencyInfoSortedList = getCurrencyInfoUseCase(OrderType.Unsorted).first()

        val fakeRepositoryList = fakeRepository.getCurrencyInfoList().first()

        assertThat(currencyInfoSortedList).isEqualTo(fakeRepositoryList)
    }

    @Test
    fun `Sort currency by title descending, correct order`() = runBlocking {
        val currencyInfo = getCurrencyInfoUseCase(OrderType.NameDescending).first()

        for (i in 0..currencyInfo.size - 2) {
            assertThat(currencyInfo[i].name).isGreaterThan(currencyInfo[i + 1].name)
        }
    }

    @Test
    fun `Sort currency by name ascending, correct order`() = runBlocking {
        val currencyInfo = getCurrencyInfoUseCase(OrderType.NameAscending).first()

        for (i in 0..currencyInfo.size - 2) {
            assertThat(currencyInfo[i].name).isLessThan(currencyInfo[i + 1].name)
        }
    }
}