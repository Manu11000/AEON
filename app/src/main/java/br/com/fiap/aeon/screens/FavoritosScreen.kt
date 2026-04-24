package com.aeon.app.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import androidx.navigation.NavController

@Composable
fun FavoritosScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF0FFF5))
            .padding(top = 16.dp, start = 16.dp, end = 16.dp)
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(Icons.Default.KeyboardArrowLeft, contentDescription = "Voltar", modifier = Modifier.size(32.dp))
            }
            Text("Favoritos", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color.Black)


            Box(modifier = Modifier.size(35.dp).clip(CircleShape).background(Color.DarkGray))
        }


        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .height(1.dp)
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(Color(0xFFFFD600), Color(0xFF750D8F))
                    )
                )
        )

        //Lista de favoritos
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(listaFavoritos) { local ->
                FavoriteCard(local)
            }

            item { Spacer(modifier = Modifier.height(80.dp)) }
        }
    }
}

@Composable
fun FavoriteCard(local: LocalFavorito) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(140.dp)
            .shadow(8.dp, RoundedCornerShape(16.dp)),
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(modifier = Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier
                    .weight(0.4f)
                    .fillMaxHeight()
                    .background(Color(0xFFD9D9D9)),
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Default.Image, contentDescription = null, tint = Color.Gray, modifier = Modifier.size(48.dp))
            }

            Column(
                modifier = Modifier
                    .weight(0.6f)
                    .fillMaxHeight()
                    .background(Color(0xFF750D8F))
                    .padding(12.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text(local.nome, color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(local.nota.toString(), color = Color.White, fontSize = 14.sp)
                    Icon(Icons.Default.Star, contentDescription = null, tint = Color(0xFFFFD600), modifier = Modifier.size(16.dp))
                    Text(" (${local.avaliacoes}+)", color = Color.White.copy(alpha = 0.7f), fontSize = 12.sp)
                    Spacer(modifier = Modifier.weight(1f))
                    Text(local.preco, color = Color.White.copy(alpha = 0.7f), fontSize = 12.sp)
                }

                Spacer(modifier = Modifier.height(4.dp))
                Text("Abre às ${local.horario}", color = Color.White, fontSize = 12.sp)
            }
        }
    }
}

// Modelo de dados para teste
data class LocalFavorito(val nome: String, val nota: Double, val avaliacoes: Int, val preco: String, val horario: String)

val listaFavoritos = listOf(
    LocalFavorito("Terraço Jardins", 5.0, 1000, "R$200-250", "14:00"),
    LocalFavorito("Hamburgueria", 4.5, 150, "R$50-55", "14:00"),
    LocalFavorito("Exposição MIS", 4.2, 500, "R$20-25", "14:00")
)