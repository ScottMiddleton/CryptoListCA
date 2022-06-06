package com.example.cryptolistca.feature_currency_info.presentation.currency_list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptolistca.feature_currency_info.domain.util.DispatcherProvider
import com.example.cryptolistca.feature_currency_info.domain.model.CurrencyInfo
import com.example.cryptolistca.feature_currency_info.domain.use_case.CurrencyInfoUseCases
import com.example.cryptolistca.feature_currency_info.domain.util.OrderType
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CurrencyListViewModel(
    private val currencyInfoUseCases: CurrencyInfoUseCases,
    private val dispatchers: DispatcherProvider
) : ViewModel() {

    private var _currencyInfoFlow = MutableStateFlow<List<CurrencyInfo>>(emptyList())
    val currencyInfoFlow = _currencyInfoFlow.asStateFlow()

    private var _initLoadingUI = MutableLiveData(false)
    val initLoadingUI = _initLoadingUI

    private var currencyInfoJob: Job? = null

    var currentOrderType: OrderType = OrderType.Unsorted

    fun onLoad() {
        viewModelScope.launch(dispatchers.main) {
            isLoading(true)

            withContext(dispatchers.io) {
                getCurrencyInfo(OrderType.Unsorted)
            }

            isLoading(false)
        }
    }

    fun onSort() {
        viewModelScope.launch(dispatchers.main) {
            isLoading(true)

            withContext(dispatchers.io) {
                val newOrderType: OrderType = when (currentOrderType) {
                    OrderType.NameAscending -> OrderType.NameDescending
                    OrderType.Unsorted -> OrderType.NameAscending
                    OrderType.NameDescending -> OrderType.NameAscending
                }
                getCurrencyInfo(newOrderType)
            }

            isLoading(false)
        }
    }

    private fun getCurrencyInfo(orderType: OrderType) {
        currencyInfoJob?.cancel()
        currencyInfoJob = currencyInfoUseCases.getCurrencyInfo(orderType)
            .onEach { currencyInfo ->
                _currencyInfoFlow.value = currencyInfo
                currentOrderType = orderType
            }
            .launchIn(viewModelScope)
    }

    fun insertCurrencyInfo(currencyInfoList: List<CurrencyInfo>) {
        viewModelScope.launch(dispatchers.main) {
            currencyInfoUseCases.insertCurrencyInfo(currencyInfoList)
        }
    }

    fun isLoading(loading: Boolean) {
        _initLoadingUI.value = loading
    }
}