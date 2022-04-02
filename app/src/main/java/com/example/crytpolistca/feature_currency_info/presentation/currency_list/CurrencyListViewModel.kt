package com.example.crytpolistca.feature_currency_info.presentation.currency_list

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.crytpolistca.feature_currency_info.domain.model.CurrencyInfo
import com.example.crytpolistca.feature_currency_info.domain.use_case.GetCurrencyInfo
import com.example.crytpolistca.feature_currency_info.domain.use_case.InsertCurrencyInfo
import com.example.crytpolistca.feature_currency_info.domain.util.OrderType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CurrencyListViewModel(
    private val getCurrencyInfoUseCase: GetCurrencyInfo,
    private val insertCurrencyInfoUseCase: InsertCurrencyInfo
) : ViewModel() {

    private var _currencyInfoLD = MutableLiveData<List<CurrencyInfo>>()
    val currencyInfoLD: LiveData<List<CurrencyInfo>>
        get() = _currencyInfoLD

    private var _initLoadingUI = MutableLiveData(false)
    val initLoadingUI: LiveData<Boolean>
        get() = _initLoadingUI

    private var getCurrencyInfoJob: Job? = null

    init {
        getCurrencyInfo(OrderType.Unsorted)
    }

    fun onLoad() {
        viewModelScope.launch {
            isLoading(true)

            withContext(Dispatchers.IO) {
                insertCurrencyInfoUseCase()
                getCurrencyInfo(OrderType.Unsorted)
            }

            isLoading(false)
        }
    }

    fun onSort() {
        viewModelScope.launch {
            isLoading(true)

            withContext(Dispatchers.IO) {
                getCurrencyInfo(OrderType.NameAscending)
            }

            isLoading(false)
        }
    }

    private fun getCurrencyInfo(orderType: OrderType) {
        getCurrencyInfoJob?.cancel()
        getCurrencyInfoJob = getCurrencyInfoUseCase(orderType)
            .onEach { currencyInfo ->
                _currencyInfoLD.value = currencyInfo
            }
            .launchIn(viewModelScope)
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    fun isLoading(loading: Boolean) {
        _initLoadingUI.value = loading
    }
}