package br.com.fiap.aeon.screens

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

// MODEL
data class Post(
    val nome: String,
    val info: String,
    val tempo: String,
    val texto: String,
    val likes: Int,
    val rating: Int
)

@Composable
fun MenuScreen(navController: NavController) {

    val context = LocalContext.current
    val sharedPref = context.getSharedPreferences("AeonPrefs", Context.MODE_PRIVATE)
    val nomeSalvo = sharedPref.getString("usuario_nome", "Explorador") ?: "Explorador"

    // LISTA DE POSTS DINÂMICOS
    val posts = listOf(
        Post(
            nome = "Blair Willows",
            info = "12 Avaliações • 30 Fotos",
            tempo = "Agora",
            texto = "Descobri um restaurante incrível hoje, perfeito para trabalhar e relaxar ☕.\n\n📍 Terraço Jardins",
            likes = 54,
            rating = 5
        ),
        Post(
            nome = "Ken Kashionista",
            info = "8 Avaliações • 15 Fotos",
            tempo = "2 horas atrás",
            texto = "Esse rooftop é simplesmente incrível, com uma vista absurda da cidade.\n\n📍 Vista Rooftop Bar",
            likes = 89,
            rating = 4
        ),
        Post(
            nome = "Draculaura",
            info = "6 Avaliações • 13 Fotos",
            tempo = "2 dias atrás",
            texto = "Hoje fui conhecer um dos marcos históricos de São Paulo, uma experiência cultural incrível.\n\n📍 Catedral da Sé",
            likes = 32,
            rating = 4
        )
    )

    Scaffold(
        containerColor = Color.Black
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
            ) {
                Text(
                    text = "Olá, $nomeSalvo!",
                    fontSize = 24.sp,
                    color = Color.White,
                    modifier = Modifier.padding(start = 16.dp)
                )
            }

            // feed
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
                    .background(Color(0xFFF0FFF5))
            ) {
                items(posts) { post ->
                    AeonPostCard(post = post, navController = navController)
                }
            }
        }
    }
}

@Composable
fun AeonPostCard(post: Post, navController: NavController) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {

        // HEADER DO POST
        Row(verticalAlignment = Alignment.CenterVertically) {

            Box(
                modifier = Modifier
                    .size(45.dp)
                    .clip(CircleShape)
                    .background(Color.Gray)
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column {
                Text(post.nome, fontWeight = FontWeight.Bold, color = Color.Black)
                Text(post.info, fontSize = 12.sp, color = Color.Gray)
            }

            Spacer(modifier = Modifier.weight(1f))

            Icon(Icons.Default.MoreHoriz, contentDescription = null, tint = Color.Gray)
        }

        // ESTRELAS + TEMPO
        Row(
            modifier = Modifier.padding(vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            repeat(post.rating) {
                Icon(
                    Icons.Default.Star,
                    contentDescription = null,
                    tint = Color(0xFFFFD600),
                    modifier = Modifier.size(16.dp)
                )
            }

            repeat(5 - post.rating) {
                Icon(
                    Icons.Default.Star,
                    contentDescription = null,
                    tint = Color.Gray,
                    modifier = Modifier.size(16.dp)
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            Text(post.tempo, fontSize = 10.sp, color = Color.Gray)
        }

        // TEXTO
        Text(
            text = post.texto,
            fontSize = 14.sp,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        // GRID DE IMAGENS
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .clickable {
                    navController.navigate("post_detail")
                }
        ) {

            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(4.dp))
                    .background(Color.LightGray)
            )

            Spacer(modifier = Modifier.width(4.dp))

            Column(modifier = Modifier.weight(0.6f)) {

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(4.dp))
                        .background(Color.LightGray)
                )

                Spacer(modifier = Modifier.height(4.dp))

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(4.dp))
                        .background(Color.LightGray)
                )
            }
        }

        // LIKE E SAVE
        Row(
            modifier = Modifier.padding(top = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Icon(
                Icons.Default.Favorite,
                contentDescription = null,
                tint = Color.Red,
                modifier = Modifier.size(20.dp)
            )

            Text(" ${post.likes} Curtidas", fontSize = 12.sp, color = Color.Gray)

            Spacer(modifier = Modifier.weight(1f))

            Icon(Icons.Default.BookmarkBorder, contentDescription = null, tint = Color.Gray)
        }

        Divider(
            modifier = Modifier.padding(top = 16.dp),
            color = Color.LightGray.copy(alpha = 0.3f)
        )
    }
}