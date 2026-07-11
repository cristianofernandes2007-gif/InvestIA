package com.investia.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.investia.app.ui.theme.InvestIATheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            InvestIATheme {
                InvestIAApp()
            }
        }
    }
}

@Composable
fun InvestIAApp() {

    var abaSelecionada by remember {
        mutableIntStateOf(0)
    }

    val nomes = listOf(
        "Início",
        "Favoritos",
        "Carteira",
        "IA",
        "Config."
    )

    val icones = listOf(
        "🏠",
        "⭐",
        "💼",
        "🤖",
        "⚙️"
    )

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar {
                nomes.forEachIndexed { indice, nome ->

                    NavigationBarItem(
                        selected = abaSelecionada == indice,
                        onClick = {
                            abaSelecionada = indice
                        },
                        icon = {
                            Text(text = icones[indice])
                        },
                        label = {
                            Text(text = nome)
                        }
                    )
                }
            }
        }
    ) { espacoInterno ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(espacoInterno)
        ) {

            when (abaSelecionada) {
                0 -> HomeScreen()
                1 -> FavoritesScreen()
                2 -> TelaTemporaria("Carteira")
                3 -> TelaTemporaria("Invest IA")
                4 -> TelaTemporaria("Configurações")
            }
        }
    }
}

@Composable
fun TelaTemporaria(
    titulo: String
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = titulo)
    }
}