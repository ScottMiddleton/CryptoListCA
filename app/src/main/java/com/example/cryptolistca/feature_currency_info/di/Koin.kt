package com.example.cryptolistca.feature_currency_info.di

import androidx.room.Room
import com.example.cryptolistca.feature_currency_info.data.data_source.CurrencyDatabase
import com.example.cryptolistca.feature_currency_info.data.repository.CurrencyRepositoryImpl
import com.example.cryptolistca.feature_currency_info.domain.repository.CurrencyRepository
import com.example.cryptolistca.feature_currency_info.domain.use_case.GetCurrencyInfo
import com.example.cryptolistca.feature_currency_info.domain.use_case.InsertCurrencyInfo
import com.example.cryptolistca.feature_currency_info.presentation.currency_list.CurrencyListViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    // Room
    single {
        Room.databaseBuilder(
            androidApplication(),
            CurrencyDatabase::class.java, "currency.db"
        ).build()
    }

    // Dao
    single {
        val database = get<CurrencyDatabase>()
        database.currencyInfoDao()
    }

    // Repositories
    single<CurrencyRepository> { CurrencyRepositoryImpl(get()) }

    // UseCases
    single { GetCurrencyInfo(get()) }
    single { InsertCurrencyInfo(get()) }

    // ViewModels
    viewModel { CurrencyListViewModel(get(), get()) }
}

