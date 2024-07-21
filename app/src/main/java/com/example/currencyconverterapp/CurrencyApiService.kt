package com.example.currencyconverterapp

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CurrencyApiService {
    @GET("v6/7ec6bf0da69cc6379e71c335/latest/{base}")
    suspend fun getRates(@Path("base") base: String): CurrencyRates
}