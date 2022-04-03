package com.example.cryptolistca.feature_currency_info.domain.use_case

import com.example.cryptolistca.feature_currency_info.data.repository.FakeCurrencyRepository
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
            fakeRepository.insertCurrencyInfoList()
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