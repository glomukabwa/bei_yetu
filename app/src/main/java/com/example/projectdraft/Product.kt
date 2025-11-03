package com.example.projectdraft

data class StorePrice(
    val storeName: String,
    val price: Double,
    val productUrl: String
)

data class Product(
    val name: String,
    val imageUrl: String?,
    val prices: List<StorePrice>
)
