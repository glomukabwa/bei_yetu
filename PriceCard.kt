// PriceCard.kt
package com.example.projectdraft.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.projectdraft.DisplayPriceItem

@Composable
fun PriceCard(
    item: DisplayPriceItem,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(modifier = Modifier.padding(16.dp)) {
            // Optional image placeholder
            Box(
                modifier = Modifier
                    .size(64.dp)
                    .background(MaterialTheme.colorScheme.secondary.copy(alpha = 0.2f))
            )

            Spacer(Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(item.productName, style = MaterialTheme.typography.titleMedium)
                Text("Store: ${item.storeName}", style = MaterialTheme.typography.bodyMedium)
                Text("Price: KES ${item.price}", style = MaterialTheme.typography.bodyLarge)
            }
        }
    }
}
