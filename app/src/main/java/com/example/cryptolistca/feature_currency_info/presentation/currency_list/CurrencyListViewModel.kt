package com.example.cryptolistca.feature_currency_info.presentation.currency_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptolistca.feature_currency_info.data.repository.DispatcherProvider
import com.example.cryptolistca.feature_currency_info.domain.model.CurrencyInfo
import com.example.cryptolistca.feature_currency_info.domain.use_case.CurrencyInfoUseCases
import com.example.cryptolistca.feature_currency_info.domain.util.OrderType
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CurrencyListViewModel(
    private val currencyInfoUseCases: CurrencyInfoUseCases,
    private val dispatchers: DispatcherProvider
) : ViewModel() {

    private var _currencyInfoLD = MutableLiveData<List<CurrencyInfo>>()
    val currencyInfoLD: LiveData<List<CurrencyInfo>>
        get() = _currencyInfoLD

    private var _initLoadingUI = MutableLiveData(false)
    val initLoadingUI = _initLoadingUI

    private var currencyInfoJob: Job? = null

    var currentOrderType: OrderType = OrderType.Unsorted

    fun onLoad() {
        viewModelScope.launch {
            isLoading(true)

            withContext(dispatchers.io) {
                getCurrencyInfo(OrderType.Unsorted)
            }

            isLoading(false)
        }
    }

    fun onSort() {
        viewModelScope.launch {
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
                _currencyInfoLD.value = currencyInfo
                currentOrderType = orderType
            }
            .launchIn(viewModelScope)
    }

    fun insertCurrencyInfo(currencyInfoList: List<CurrencyInfo>) {
        viewModelScope.launch {
            currencyInfoUseCases.insertCurrencyInfo(currencyInfoList)
        }
    }

    fun isLoading(loading: Boolean) {
        _initLoadingUI.value = loading
    }
}