package com.poiasd.currencyapidbdemo.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "currency", indices = [Index(value = ["alpha3Code"], unique = true)])
data class CurrencyDbEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "alpha3Code")
    val alpha3Code: String,
    @ColumnInfo(name = "nameInRussian")
    val nameInRussian: String,
    @ColumnInfo(name = "denomination")
    val denomination: Int,
    @ColumnInfo(name = "valueInRub")
    val valueInRub: Double,
)
