package com.example.cryptolistca.feature_currency_info.data.data_source

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.cryptolistca.feature_currency_info.domain.model.CurrencyInfo
import kotlinx.coroutines.flow.Flow

@Dao
interface CurrencyInfoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCurrencyInfoList(currencyInfoList: List<CurrencyInfo>)

    @Query("SELECT * FROM currency_info")
    fun getCurrencyInfoList(): Flow<List<CurrencyInfo>>
}