package com.example.currencyconverterapp.repository

import com.example.currencyconverterapp.network.CurrencyApiService
import com.example.currencyconverterapp.model.CurrencyRates
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class CurrencyRepository @Inject constructor(private val apiService: CurrencyApiService) {

    private val ioDispatcher: CoroutineContext = Dispatchers.IO

    suspend fun getRates(base: String): CurrencyRates {
        return withContext(ioDispatcher) {
            apiService.getRates(base)
        }
    }
}