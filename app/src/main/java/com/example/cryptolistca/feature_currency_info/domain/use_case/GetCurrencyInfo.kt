package com.example.cryptolistca.feature_currency_info.domain.use_case

import com.example.cryptolistca.feature_currency_info.domain.model.CurrencyInfo
import com.example.cryptolistca.feature_currency_info.domain.repository.CurrencyRepository
import com.example.cryptolistca.feature_currency_info.domain.util.OrderType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetCurrencyInfo(
    private val repository: CurrencyRepository
) {
    operator fun invoke(orderType: OrderType): Flow<List<CurrencyInfo>> {
        return repository.getCurrencyInfoList().map { currencyInfo ->
            when (orderType) {
                is OrderType.Unsorted -> {
                    currencyInfo
                }
                is OrderType.NameAscending -> {
                    currencyInfo.sortedBy { it.name.lowercase() }
                }
                OrderType.NameDescending -> {
                    currencyInfo.sortedByDescending { it.name.lowercase() }
                }
            }
        }
    }
}