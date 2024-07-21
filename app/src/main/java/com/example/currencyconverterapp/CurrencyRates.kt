package com.example.currencyconverterapp

import com.google.gson.annotations.SerializedName

data class CurrencyRates(
    @SerializedName("conversion_rates") val rates: Map<String, Double>
)