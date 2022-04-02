package com.example.cryptolistca.feature_currency_info.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.cryptolistca.feature_currency_info.domain.model.CurrencyInfo

@Database(entities = [CurrencyInfo::class], version = 1)
abstract class CurrencyDatabase : RoomDatabase() {
    abstract fun currencyInfoDao(): CurrencyInfoDao
}