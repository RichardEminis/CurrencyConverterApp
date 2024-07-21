package com.example.currencyconverterapp

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext

class CurrencyRepository @Inject constructor(private val apiService: CurrencyApiService) {

    private val ioDispatcher: CoroutineContext = Dispatchers.IO

    suspend fun getRates(base: String): CurrencyRates {
        return withContext(ioDispatcher) {
            apiService.getRates(base)
        }
    }
}