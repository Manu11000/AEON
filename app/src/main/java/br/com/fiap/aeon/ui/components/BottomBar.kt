package br.com.fiap.aeon.ui.components

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.IconButton
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.draw.clip
import androidx.compose.material3.Icon


@Composable
fun AeonBottomBar(navController: NavController) {
    // 1. Criamos um estado "manual" para a rota
    var currentRoute by remember { mutableStateOf<String?>(null) }


    DisposableEffect(navController) {
        val listener = NavController.OnDestinationChangedListener { _, destination, _ ->
            currentRoute = destination.route
            // Log pra ver a tela
            Log.d("AEON_NAV", "TELA MUDOU PARA: ${destination.route}")
        }

        navController.addOnDestinationChangedListener(listener)

        // Limpeza pra não dar conflito
        onDispose {
            navController.removeOnDestinationChangedListener(listener)
        }
    }

    val telasPrincipais = listOf("menu", "map", "review", "favoritos", "perfil")

    if (currentRoute in telasPrincipais) {
        androidx.compose.material.BottomAppBar(
            backgroundColor = Color.Black,
            modifier = Modifier.height(120.dp),
            elevation = 8.dp,
            contentPadding = PaddingValues(0.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Ícones

                IconButton(onClick = { navController.navigate("menu") }) {
                    Icon(Icons.Default.Home, contentDescription = null, tint = Color.White)
                }


                IconButton(onClick = { navController.navigate("map") }) {
                    Icon(Icons.Default.Search, contentDescription = null, tint = Color.White)
                }


                IconButton(onClick = { navController.navigate("review") }) {
                    Icon(
                        Icons.Default.AddCircleOutline,
                        contentDescription = "Fazer Review",
                        tint = Color.White,
                        modifier = Modifier.size(35.dp)
                    )
                }

                IconButton(onClick = { navController.navigate("favoritos") }) {
                    Icon(
                        Icons.Default.BookmarkBorder,
                        contentDescription = null,
                        tint = Color.White
                    )
                }



                    IconButton(onClick = { navController.navigate("perfil") }) {

                        Box(
                            modifier = Modifier
                                .size(30.dp)
                                .clip(CircleShape)
                                .background(Color.Gray)
                                .border(
                                    1.dp,
                                    Color.White,
                                    CircleShape
                                )
                        )
                    }


            }
        }
    }
}
