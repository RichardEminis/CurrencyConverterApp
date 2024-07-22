package com.example.currencyconverterapp.network

import com.example.currencyconverterapp.model.CurrencyRates
import com.example.currencyconverterapp.utils.API_KEY
import retrofit2.http.GET
import retrofit2.http.Path

interface CurrencyApiService {
    @GET("v6/$API_KEY/latest/{base}")
    suspend fun getRates(@Path("base") base: String): CurrencyRates
}