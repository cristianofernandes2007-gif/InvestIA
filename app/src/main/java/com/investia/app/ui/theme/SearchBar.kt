package com.investia.app.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun SearchBar(
    ticker: String,
    onTickerChange: (String) -> Unit,
    onPesquisar: () -> Unit
) {

    OutlinedTextField(
        value = ticker,
        onValueChange = onTickerChange,
        label = { Text("Código da ação") },
        modifier = Modifier.fillMaxWidth()
    )

    Button(
        onClick = onPesquisar,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text("Pesquisar")
    }
}