package com.example.cryptolistca.feature_currency_info.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "currency_info")
data class CurrencyInfo(
    @PrimaryKey
    val id: String,
    val name: String,
    val symbol: String,
)