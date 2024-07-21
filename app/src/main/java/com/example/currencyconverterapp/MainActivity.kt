package com.example.currencyconverterapp

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.currencyconverterapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: CurrencyViewModel by viewModels()

    private var _binding: ActivityMainBinding? = null
    private val binding
        get() = _binding
            ?: throw IllegalStateException("Binding for ActivityMainBinding must not be null")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUI()

        viewModel.convertedAmount.observe(this) { convertedAmount ->
            binding.resultTextView.text = convertedAmount?.convertedAmount?.toString() ?: ""
        }
    }

    private fun setupUI() {
        val currencies = listOf("USD", "EUR", "RUB")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, currencies)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        binding.inputCurrencySpinner.adapter = adapter
        binding.resultCurrencySpinner.adapter = adapter

        binding.convertButton.setOnClickListener {
            val amount = binding.amountEditText.text.toString().toDoubleOrNull()
            val baseCurrency = binding.inputCurrencySpinner.selectedItem.toString()
            val targetCurrency = binding.resultCurrencySpinner.selectedItem.toString()

            if (amount != null) {
                viewModel.convertCurrency(amount, baseCurrency, targetCurrency)
            }
        }
    }
}