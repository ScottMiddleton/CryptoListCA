package com.example.crytpolistca

import android.app.Application
import com.example.crytpolistca.feature_currency_info.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

open class CryptoListApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@CryptoListApplication)
            modules(appModule)
        }
    }
}