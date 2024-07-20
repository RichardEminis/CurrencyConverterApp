package com.example.currencyconverterapp

data class CurrencyRates(
    val rates: Map<String, Double>,
    val base: String,
    val date: String
)