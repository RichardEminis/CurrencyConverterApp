package com.example.currencyconverterapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

data class CurrencyState(
    val convertedAmount: Double? = null
)

@HiltViewModel
class CurrencyViewModel @Inject constructor(
    private val repository: CurrencyRepository
) : ViewModel() {

    private val _convertedAmount = MutableLiveData(CurrencyState())
    private val convertedAmount: LiveData<CurrencyState>
        get() = _convertedAmount

    fun convertCurrency(amount: Double, input: String, result: String) {
        viewModelScope.launch {
            val rate = repository.getRates(input).rates[result]
            if (rate != null) {
                _convertedAmount.value =
                    convertedAmount.value?.copy(convertedAmount = amount * rate)
            }
        }
    }
}