package com.example.cryptolistca.feature_currency_info.presentation.currency_list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import app.cash.turbine.test
import com.example.cryptolistca.feature_currency_info.data.repository.FakeCurrencyRepository
import com.example.cryptolistca.feature_currency_info.di.TestDispatchers
import com.example.cryptolistca.feature_currency_info.data.local.entity.CurrencyInfoEntity
import com.example.cryptolistca.feature_currency_info.domain.use_case.CurrencyInfoUseCases
import com.example.cryptolistca.feature_currency_info.domain.use_case.GetCurrencyInfo
import com.example.cryptolistca.feature_currency_info.domain.use_case.InsertCurrencyInfo
import com.example.cryptolistca.feature_currency_info.domain.util.OrderType
import com.example.cryptolistca.feature_currency_info.utils.getOrAwaitValue
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CurrencyListViewModelTest {

    private lateinit var viewModel: CurrencyListViewModel
    private lateinit var getCurrencyUseCase: GetCurrencyInfo
    private lateinit var insertCurrencyUseCase: InsertCurrencyInfo
    private lateinit var repository: FakeCurrencyRepository
    private lateinit var dispatchers: TestDispatchers

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        repository = FakeCurrencyRepository()

        runBlocking {
            val currencyInfoList = mutableListOf<CurrencyInfoEntity>()

            currencyInfoList.add(CurrencyInfoEntity("BTC", "Bitcoin", "BTC"))
            currencyInfoList.add(CurrencyInfoEntity("ETH", "Ethereum", "ETH"))
            currencyInfoList.add(CurrencyInfoEntity("XRP", "XRP", "XRP"))
            currencyInfoList.add(CurrencyInfoEntity("BCH", "Bitcoin Cash", "BCH"))
            currencyInfoList.add(CurrencyInfoEntity("LTC", "Litecoin", "LTC"))
            currencyInfoList.add(CurrencyInfoEntity("EOS", "EOS", "EOS"))
            currencyInfoList.add(CurrencyInfoEntity("BNB", "Binance Coin", "BNB"))
            currencyInfoList.add(CurrencyInfoEntity("LINK", "Chainlink", "LINK"))
            currencyInfoList.add(CurrencyInfoEntity("NEO", "NEO", "NEO"))
            currencyInfoList.add(CurrencyInfoEntity("ETC", "Ethereum Classic", "ETC"))
            currencyInfoList.add(CurrencyInfoEntity("ONT", "Ontology", "ONT"))
            currencyInfoList.add(CurrencyInfoEntity("CRO", "Crypto.com Chain", "CRO"))
            currencyInfoList.add(CurrencyInfoEntity("CUC", "Cucumber", "CUC"))
            currencyInfoList.add(CurrencyInfoEntity("USDC", "USD Coin", "USDC"))

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
    fun onLoad_getCurrencyInfo_unsorted() = runBlocking {
        viewModel.onLoad()

        viewModel.currencyInfoFlow.test {
            assertThat(awaitItem()).isNotEmpty()
            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun onSort_getCurrencyInfo_sortedAscending() = runBlocking {
        viewModel.currentOrderType = OrderType.NameDescending
        viewModel.onSort()

        viewModel.currencyInfoFlow.test {
            val emission = awaitItem()
            for (i in 0..emission.size - 2) {
                assertThat(emission[i].name).isLessThan(emission[i + 1].name)
            }
            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun onSort_toggleAscendingToDescending() = runBlocking {
        viewModel.currentOrderType = OrderType.Unsorted

        viewModel.onSort()

        viewModel.currencyInfoFlow.test {
            val emission = awaitItem()
            for (i in 0..emission.size - 2) {
                assertThat(emission[i].name).isLessThan(emission[i + 1].name)
            }
            cancelAndConsumeRemainingEvents()
        }

        viewModel.onSort()

        viewModel.currencyInfoFlow.test {
            val emission = awaitItem()
            for (i in 0..emission.size - 2) {
                assertThat(emission[i].name).isGreaterThan(emission[i + 1].name)
            }
            cancelAndConsumeRemainingEvents()
        }
    }
}