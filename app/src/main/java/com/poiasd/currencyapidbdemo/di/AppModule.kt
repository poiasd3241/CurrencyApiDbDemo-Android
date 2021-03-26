package com.poiasd.currencyapidbdemo.di

import android.app.Application
import androidx.room.Room
import com.poiasd.currencyapidbdemo.data.db.CurrencyDatabase
import com.poiasd.currencyapidbdemo.data.db.CurrencyDao
import com.poiasd.currencyapidbdemo.data.db.CurrencyDbEntityMapper
import com.poiasd.currencyapidbdemo.data.network.CurrencyApi
import com.poiasd.currencyapidbdemo.data.network.CurrencyNetworkEntityMapper
import com.poiasd.currencyapidbdemo.data.repository.CurrencyRepository
import com.poiasd.currencyapidbdemo.data.repository.DefaultCurrencyRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

private const val BASE_URL = "https://www.cbr-xml-daily.ru/"

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideCurrencyAPI(): CurrencyApi =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CurrencyApi::class.java)

    @Provides
    @Singleton
    fun provideDatabase(app: Application): CurrencyDatabase =
        Room.databaseBuilder(app, CurrencyDatabase::class.java, "currencyapidbdemoDatabase")
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    fun provideCurrencyDao(db: CurrencyDatabase) = db.currencyDao()

    @Singleton
    @Provides
    fun provideCurrencyRepository(
        dao: CurrencyDao,
        api: CurrencyApi,
        dbEntityMapper: CurrencyDbEntityMapper,
        networkEntityMapper: CurrencyNetworkEntityMapper
    ): CurrencyRepository =
        DefaultCurrencyRepository(dao, api, dbEntityMapper, networkEntityMapper)
}
