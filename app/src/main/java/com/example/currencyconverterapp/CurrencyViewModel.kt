package com.example.currencyconverterapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CurrencyViewModel @Inject constructor(
    private val repository: CurrencyRepository
) : ViewModel() {

    private val _convertedAmount = MutableLiveData<Double>()
    val convertedAmount: LiveData<Double> get() = _convertedAmount

    fun convertCurrency(amount: Double, input: String, result: String) {
        TODO()
    }
}