package com.example.projectdraft

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projectdraft.Product
import com.example.projectdraft.Repository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ResultsViewModel(
    private val repository: Repository = Repository()
) : ViewModel() {

    private val _products = MutableStateFlow<List<Product>>(emptyList())
    val products: StateFlow<List<Product>> = _products

    fun search(query: String) {
        viewModelScope.launch {
            val results = repository.searchProducts(query)
            _products.value = results
        }
    }
}
