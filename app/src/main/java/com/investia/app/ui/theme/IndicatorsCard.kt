package com.investia.app.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun IndicatorsCard(
    abertura: String,
    maxima: String,
    minima: String,
    fechamento: String,
    volume: String
) {

    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {

        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            Text(
                text = "📊 Indicadores",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(12.dp))

            Indicador("Abertura", abertura)
            Indicador("Máxima", maxima)
            Indicador("Mínima", minima)
            Indicador("Fechamento", fechamento)
            Indicador("Volume", volume)

        }

    }

}

@Composable
private fun Indicador(
    titulo: String,
    valor: String
) {

    Column {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Text(titulo)

            Text(
                text = valor,
                fontWeight = FontWeight.Bold
            )

        }

        Spacer(modifier = Modifier.height(6.dp))

        HorizontalDivider()

        Spacer(modifier = Modifier.height(6.dp))

    }

}