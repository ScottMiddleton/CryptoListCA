package com.example.cryptolistca.feature_currency_info.presentation.currency_list

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.cryptolistca.feature_currency_info.data.repository.FakeCurrencyRepository
import com.example.cryptolistca.feature_currency_info.di.TestDispatchers
import com.example.cryptolistca.feature_currency_info.domain.model.CurrencyInfo
import com.example.cryptolistca.feature_currency_info.domain.use_case.CurrencyInfoUseCases
import com.example.cryptolistca.feature_currency_info.domain.use_case.GetCurrencyInfo
import com.example.cryptolistca.feature_currency_info.domain.use_case.InsertCurrencyInfo
import com.example.cryptolistca.feature_currency_info.domain.util.OrderType
import com.example.cryptolistca.feature_currency_info.utils.getOrAwaitValue
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CurrencyListViewModelTest {

    private lateinit var viewModel: CurrencyListViewModel
    private lateinit var getCurrencyUseCase: GetCurrencyInfo
    private lateinit var insertCurrencyUseCase: InsertCurrencyInfo
    private lateinit var repository: FakeCurrencyRepository
    private lateinit var dispatchers: TestDispatchers

    @Before
    fun setup() {
        repository = FakeCurrencyRepository()

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

            repository.insertCurrencyInfoList(currencyInfoList)
        }

        getCurrencyUseCase = GetCurrencyInfo(repository)
        insertCurrencyUseCase = InsertCurrencyInfo(repository)
        val currencyInfoUseCases = CurrencyInfoUseCases(getCurrencyUseCase, insertCurrencyUseCase)

        dispatchers = TestDispatchers()

        viewModel = CurrencyListViewModel(currencyInfoUseCases, dispatchers)
    }

    @Test
    fun isLoadingTrue_liveDataTrue() {
        viewModel.isLoading(true)

        val value = viewModel.initLoadingUI.getOrAwaitValue()

        assertThat(value).isEqualTo(true)
    }

    @Test
    fun isLoadingFalse_liveDataFalse() {
        viewModel.isLoading(false)

        val value = viewModel.initLoadingUI.getOrAwaitValue()

        assertThat(value).isEqualTo(false)
    }

    @Test
    fun onLoad_getCurrencyInfo_unsorted() {
            viewModel.onLoad()
            val value = viewModel.currencyInfoLD.getOrAwaitValue()
            assertThat(value).isNotEmpty()
    }

    @Test
    fun onSort_getCurrencyInfo_sortedAscending() {
        viewModel.currentOrderType = OrderType.NameDescending
        viewModel.onSort()
        val value = viewModel.currencyInfoLD.getOrAwaitValue()

        for (i in 0..value.size - 2) {
            assertThat(value[i].name).isLessThan(value[i + 1].name)
        }
    }

    @Test
    fun onSort_toggleAscendingToDescending() {
        viewModel.currentOrderType = OrderType.Unsorted
        viewModel.onSort()
        val valueAscending = viewModel.currencyInfoLD.getOrAwaitValue()

        for (i in 0..valueAscending.size - 2) {
            assertThat(valueAscending[i].name).isLessThan(valueAscending[i + 1].name)
        }

        viewModel.onSort()
        val valueDescending = viewModel.currencyInfoLD.getOrAwaitValue()

        for (i in 0..valueDescending.size - 2) {
            assertThat(valueDescending[i].name).isGreaterThan(valueDescending[i + 1].name)
        }
    }
}