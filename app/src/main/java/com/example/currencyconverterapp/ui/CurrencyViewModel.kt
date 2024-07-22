package com.example.currencyconverterapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.currencyconverterapp.repository.CurrencyRepository
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
    val convertedAmount: LiveData<CurrencyState>
        get() = _convertedAmount

    fun convertCurrency(amount: Double, base: String, target: String) {
        viewModelScope.launch {
            try {
                val rate = repository.getRates(base).rates[target]
                if (rate != null) {
                    _convertedAmount.value =
                        convertedAmount.value?.copy(convertedAmount = amount * rate)
                }

            } catch (e: Exception) {
                _convertedAmount.value = convertedAmount.value?.copy(convertedAmount = null)
            }
        }
    }
}