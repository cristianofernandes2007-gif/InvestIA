package com.investia.app

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.investia.app.model.Acao
import com.investia.app.network.RetrofitClient
import com.investia.app.ui.Header
import com.investia.app.ui.IndicatorsCard
import com.investia.app.ui.PriceChart
import com.investia.app.ui.ScoreCard
import com.investia.app.ui.SearchBar
import com.investia.app.ui.StockCard
import kotlinx.coroutines.launch
import java.util.Locale

@Composable
fun HomeScreen() {

    var ticker by remember { mutableStateOf("PETR4") }
    var acao by remember { mutableStateOf<Acao?>(null) }
    var carregando by remember { mutableStateOf(false) }
    var erro by remember { mutableStateOf("") }

    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {

        Header()

        SearchBar(
            ticker = ticker,
            onTickerChange = {
                ticker = it.uppercase(Locale.ROOT)
            },
            onPesquisar = {
                val tickerLimpo = ticker.trim()

                if (tickerLimpo.isBlank()) {
                    erro = "Digite o código de um ativo."
                } else {
                    scope.launch {
                        carregando = true
                        erro = ""
                        acao = null

                        try {
                            acao = RetrofitClient.api
                                .buscarCotacao(tickerLimpo)
                                .results
                                .firstOrNull()

                            if (acao == null) {
                                erro = "Ativo não encontrado."
                            }
                        } catch (e: Exception) {
                            erro = "Erro ao buscar a cotação."
                        } finally {
                            carregando = false
                        }
                    }
                }
            }
        )

        Spacer(modifier = Modifier.height(20.dp))

        if (carregando) {
            CircularProgressIndicator()
            Spacer(modifier = Modifier.height(12.dp))
            Text(text = "Buscando ${ticker.trim()}...")
        }

        if (erro.isNotEmpty()) {
            Text(text = erro)
        }

        acao?.let { ativo ->

            StockCard(
                ticker = ativo.symbol,
                empresa = ativo.longName ?: "Empresa não informada",
                preco = formatarPreco(ativo.regularMarketPrice),
                variacao = formatarPercentual(
                    ativo.regularMarketChangePercent
                )
            )

            Spacer(modifier = Modifier.height(18.dp))

            IndicatorsCard(
                abertura = formatarPreco(
                    ativo.regularMarketOpen
                ),
                maxima = formatarPreco(
                    ativo.regularMarketDayHigh
                ),
                minima = formatarPreco(
                    ativo.regularMarketDayLow
                ),
                fechamento = formatarPreco(
                    ativo.regularMarketPreviousClose
                ),
                volume = formatarVolume(
                    ativo.regularMarketVolume
                )
            )

            Spacer(modifier = Modifier.height(18.dp))

            PriceChart(
                valores = criarValoresGrafico(
                    ativo.regularMarketPrice
                )
            )

            Spacer(modifier = Modifier.height(18.dp))

            ScoreCard(
                score = 9.4,
                recomendacao = "🟢 COMPRAR"
            )

            Spacer(modifier = Modifier.height(30.dp))
        }
    }
}

private fun criarValoresGrafico(
    precoAtual: Double?
): List<Float> {

    val preco = precoAtual?.toFloat() ?: 30f

    return listOf(
        preco * 0.92f,
        preco * 0.95f,
        preco * 0.93f,
        preco * 0.97f,
        preco * 0.96f,
        preco * 1.01f,
        preco * 0.99f,
        preco * 1.03f,
        preco * 1.02f,
        preco
    )
}

private fun formatarPreco(valor: Double?): String {
    return if (valor == null) {
        "Não disponível"
    } else {
        String.format(
            Locale("pt", "BR"),
            "R$ %.2f",
            valor
        )
    }
}

private fun formatarPercentual(valor: Double?): String {
    return if (valor == null) {
        "Não disponível"
    } else {
        String.format(
            Locale("pt", "BR"),
            "%+.2f%%",
            valor
        )
    }
}

private fun formatarVolume(valor: Double?): String {
    if (valor == null) return "Não disponível"

    return when {
        valor >= 1_000_000_000 ->
            String.format(
                Locale("pt", "BR"),
                "%.2f bilhões",
                valor / 1_000_000_000
            )

        valor >= 1_000_000 ->
            String.format(
                Locale("pt", "BR"),
                "%.2f milhões",
                valor / 1_000_000
            )

        valor >= 1_000 ->
            String.format(
                Locale("pt", "BR"),
                "%.2f mil",
                valor / 1_000
            )

        else ->
            String.format(
                Locale("pt", "BR"),
                "%.0f",
                valor
            )
    }
}