package com.example.projectdraft

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp

@Composable
fun SearchScreen(onSearch: (String) -> Unit) {
    var text by remember { mutableStateOf(TextFieldValue("")) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Beiyetu Price Comparison", style = MaterialTheme.typography.headlineSmall)
        Spacer(Modifier.height(20.dp))

        OutlinedTextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("Search product") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(10.dp))

        Button(
            onClick = { if (text.text.isNotBlank()) onSearch(text.text) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Compare Prices")
        }
    }
}
