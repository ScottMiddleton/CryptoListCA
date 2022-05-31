package com.example.cryptolistca.feature_currency_info.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.cryptolistca.feature_currency_info.data.local.entity.CurrencyInfoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CurrencyInfoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCurrencyInfoList(currencyInfoList: List<CurrencyInfoEntity>)

    @Query("SELECT * FROM currency_info")
    fun getCurrencyInfoList(): Flow<List<CurrencyInfoEntity>>
}