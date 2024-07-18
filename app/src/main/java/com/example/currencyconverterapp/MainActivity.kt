package com.example.currencyconverterapp

//noinspection SuspiciousImport
import android.R
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.currencyconverterapp.databinding.ActivityMainBinding

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
    }

    private fun setupUI() {
        val currencies = listOf("USD", "EUR", "GBP")
        val adapter = ArrayAdapter(this, R.layout.simple_spinner_item, currencies)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        binding.inputCurrencySpinner.adapter = adapter
        binding.resultCurrencySpinner.adapter = adapter

        binding.convertButton.setOnClickListener {
            val amount = binding.amountEditText.text.toString().toDoubleOrNull()
            val input = binding.inputCurrencySpinner.selectedItem.toString()
            val result = binding.resultCurrencySpinner.selectedItem.toString()

            if (amount != null) {
                viewModel.convertCurrency(amount, input, result)
            }
        }
    }
}