package com.example.cryptolistca.feature_currency_info.di

import com.example.cryptolistca.feature_currency_info.data.repository.DispatcherProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher

class TestDispatchers : DispatcherProvider {
    @OptIn(ExperimentalCoroutinesApi::class)
    private val testDispatchers = UnconfinedTestDispatcher()
    override val main: CoroutineDispatcher
        get() = testDispatchers
    override val io: CoroutineDispatcher
        get() = testDispatchers
    override val default: CoroutineDispatcher
        get() = testDispatchers
}