package com.example.cryptolistca.feature_currency_info.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.cryptolistca.feature_currency_info.data.local.entity.CurrencyInfoEntity

@Database(entities = [CurrencyInfoEntity::class], version = 1)
abstract class CurrencyDatabase : RoomDatabase() {
    abstract fun currencyInfoDao(): CurrencyInfoDao
}