package com.example.projectdraft.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.projectdraft.DisplayPriceItem

@Composable
fun SearchResultsScreen(
    query: String,
    products: List<com.example.projectdraft.Product>,
    onItemClick: (DisplayPriceItem) -> Unit
) {
    var selectedSort by remember { mutableStateOf("Price: Low→High") }

    val displayItems = remember(products, selectedSort) {
        val flattened = products.flatMap { product ->
            product.prices.map { storePrice ->
                DisplayPriceItem(
                    productName = product.name,
                    storeName = storePrice.storeName,
                    price = storePrice.price,
                    url = storePrice.productUrl
                )
            }
        }
        when (selectedSort) {
            "Price: Low→High" -> flattened.sortedBy { it.price }
            "Price: High→Low" -> flattened.sortedByDescending { it.price }
            else -> flattened
        }
    }

    Column(modifier = Modifier.padding(16.dp)) {
        // Sort/Filter Bar
        SortFilterBar(selectedSort, onSortChange = { selectedSort = it })

        Spacer(Modifier.height(12.dp))

        Text(
            text = "Results for '$query' (${displayItems.size} total prices)",
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(Modifier.height(12.dp))

        if (displayItems.isEmpty()) {
            Text("No results found.", style = MaterialTheme.typography.bodyLarge)
        } else {
            LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                items(displayItems) { item ->
                    PriceCard(item = item, onClick = { onItemClick(item) })
                }
            }
        }
    }
}

@Composable
fun SortFilterBar(
    sortOption: String,
    onSortChange: (String) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text("Sort by:", style = MaterialTheme.typography.bodyMedium)
        DropdownMenuDemo(sortOption, onSortChange)
    }
}

@Composable
fun DropdownMenuDemo(selected: String, onSelectedChange: (String) -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    Box {
        Text(
            text = selected,
            modifier = Modifier
                .clickable { expanded = true }
                .padding(8.dp)
                .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.1f))
                .padding(horizontal = 12.dp, vertical = 8.dp)
        )
        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            listOf("Price: Low→High", "Price: High→Low").forEach { option ->
                DropdownMenuItem(onClick = {
                    expanded = false
                    onSelectedChange(option)
                }) {
                    Text(option)
                }
            }
        }
    }
}
