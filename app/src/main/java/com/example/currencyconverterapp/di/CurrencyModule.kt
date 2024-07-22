package com.example.currencyconverterapp.di

import com.example.currencyconverterapp.network.CurrencyApiService
import com.example.currencyconverterapp.utils.CURRENCY_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class CurrencyModule {

    @Provides
    fun provideHttpClient(): OkHttpClient {
        val logInterceptor = HttpLoggingInterceptor()
        logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder().addInterceptor(logInterceptor).build()
    }

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(CURRENCY_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideCurrencyApiService(retrofit: Retrofit): CurrencyApiService {
        return retrofit.create(CurrencyApiService::class.java)
    }
}