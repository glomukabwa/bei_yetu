package com.example.projectdraft

import kotlinx.coroutines.delay

class Repository {
    suspend fun searchProducts(query: String): List<Product> {
        delay(500) // simulate network delay
        return listOf(
            Product(
                name = "$query 2kg",
                imageUrl = null,
                prices = listOf(
                    StorePrice("Jumia", 450.0, "https://jumia.co.ke/product1"),
                    StorePrice("Kilimall", 470.0, "https://kilimall.co.ke/product1")
                )
            ),
            Product(
                name = "$query 1kg",
                imageUrl = null,
                prices = listOf(
                    StorePrice("Naivas", 230.0, "https://naivas.co.ke/product2"),
                    StorePrice("ChekiPrice", 250.0, "https://chekiprice.co.ke/product2")
                )
            )
        )
    }
}
