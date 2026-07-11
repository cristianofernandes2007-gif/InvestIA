package com.investia.app.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PriceChart(
    valores: List<Float>
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "📈 Histórico de preços",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            Canvas(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp)
            ) {
                if (valores.size < 2) return@Canvas

                val menor = valores.minOrNull() ?: 0f
                val maior = valores.maxOrNull() ?: 1f
                val intervalo = (maior - menor).takeIf {
                    it > 0f
                } ?: 1f

                val distanciaX =
                    size.width / (valores.size - 1)

                val caminho = Path()

                valores.forEachIndexed { indice, valor ->

                    val x = indice * distanciaX

                    val y = size.height -
                            ((valor - menor) / intervalo) *
                            size.height

                    if (indice == 0) {
                        caminho.moveTo(x, y)
                    } else {
                        caminho.lineTo(x, y)
                    }
                }

                drawPath(
                    path = caminho,
                    color = androidx.compose.ui.graphics.Color(
                        0xFF1565C0
                    ),
                    style = Stroke(
                        width = 6f
                    )
                )

                valores.forEachIndexed { indice, valor ->

                    val x = indice * distanciaX

                    val y = size.height -
                            ((valor - menor) / intervalo) *
                            size.height

                    drawCircle(
                        color =
                            androidx.compose.ui.graphics.Color(
                                0xFF1565C0
                            ),
                        radius = 7f,
                        center = Offset(x, y)
                    )
                }
            }
        }
    }
}