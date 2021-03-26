package com.poiasd.currencyapidbdemo.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CurrencyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(currencyDbEntity: CurrencyDbEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(currencyDbEntities: List<CurrencyDbEntity>)

    @Query("SELECT * FROM currency")
    suspend fun getCurrencies(): List<CurrencyDbEntity>

    @Query("SELECT NOT EXISTS ( SELECT 1 FROM currency )")
    suspend fun isCurrencyTableEmpty(): Boolean
}
