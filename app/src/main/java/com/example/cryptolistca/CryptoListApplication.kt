package com.example.cryptolistca

import android.app.Application
import com.example.cryptolistca.di.appModule
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