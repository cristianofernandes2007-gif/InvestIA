package com.investia.app

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun FavoritesScreen() {

    val context = LocalContext.current

    var favoritos by remember {
        mutableStateOf(
            FavoritesStore.carregar(context)
                .toList()
                .sorted()
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {

        Text(
            text = "⭐ Favoritos",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(20.dp))

        if (favoritos.isEmpty()) {

            Text("Nenhum ativo favorito.")

        } else {

            favoritos.forEach { ticker ->

                Card(
                    modifier = Modifier.fillMaxWidth()
                ) {

                    Column(
                        modifier = Modifier.padding(20.dp)
                    ) {

                        Text(
                            text = ticker,
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(
                            modifier = Modifier.height(10.dp)
                        )

                        Button(
                            onClick = {
                                val novaLista =
                                    favoritos - ticker

                                favoritos = novaLista

                                FavoritesStore.salvar(
                                    context,
                                    novaLista.toSet()
                                )
                            }
                        ) {
                            Text("Remover")
                        }
                    }
                }

                Spacer(
                    modifier = Modifier.height(12.dp)
                )
            }
        }
    }
}