package br.com.fiap.aeon.screens

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Scaffold
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext


@Composable
fun MenuScreen(navController: NavController) {
    val context = LocalContext.current
    val sharedPref = context.getSharedPreferences("AeonPrefs", Context.MODE_PRIVATE)
    val nomeSalvo = sharedPref.getString("usuario_nome", "Explorador") ?: "Explorador"

    Scaffold(
        containerColor = Color.Black // O fundo geral atrás do feed é preto
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            //Cabeçalho
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),

            ) {
                Text(
                    text = "Olá, $nomeSalvo!",
                    fontSize = 24.sp,
                    color = Color.White
                )
            }

            //Feed
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
                    .background(Color(0xFFF0FFF5))
            ) {
                items(5) {
                    AeonPostCard(navController = navController)
                }
            }
        }
    }
}

@Composable
fun AeonPostCard(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        // Cabeçalho do Post (Avatar e Nome)
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(45.dp)
                    .clip(CircleShape)
                    .background(Color.Gray) // Placeholder para foto
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text("Draculaura", fontWeight = FontWeight.Bold, color = Color.Black)
                Text("6 Avaliações . 13 Fotos", fontSize = 12.sp, color = Color.Gray)
            }
            Spacer(modifier = Modifier.weight(1f))
            Icon(Icons.Default.MoreHoriz, contentDescription = null, tint = Color.Gray)
        }

        // Estrelas e Tempo
        Row(
            modifier = Modifier.padding(vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            repeat(4) { Icon(Icons.Default.Star, null, tint = Color(0xFFFFD600), modifier = Modifier.size(16.dp)) }
            Icon(Icons.Default.Star, null, tint = Color.Gray, modifier = Modifier.size(16.dp))
            Spacer(modifier = Modifier.width(8.dp))
            Text("5 Meses Atrás", fontSize = 10.sp, color = Color.Gray)
        }

        //Texto do Post
        Text(
            text = "Hoje fui conhecer um marco histórico da nossa cidade de São Paulo - Catedral Metropolitana...",
            fontSize = 14.sp,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        //Grid do post
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .clickable {
                    navController.navigate("post_detail") //Detalhes da review
                }
        ) {
            //As caixinhas de cada foto
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(4.dp))
                    .background(Color.LightGray)
            )
            Spacer(modifier = Modifier.width(4.dp))

            Column(modifier = Modifier.weight(0.6f)) {
                Box(modifier = Modifier.weight(1f).fillMaxWidth().clip(RoundedCornerShape(4.dp)).background(Color.LightGray))
                Spacer(modifier = Modifier.height(4.dp))
                Box(modifier = Modifier.weight(1f).fillMaxWidth().clip(RoundedCornerShape(4.dp)).background(Color.LightGray))
            }
        }

        //o Like e o save (save é o favorito)
        Row(
            modifier = Modifier.padding(top = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(Icons.Default.Favorite, contentDescription = null, tint = Color.Red, modifier = Modifier.size(20.dp))
            Text(" 32 Curtidas", fontSize = 12.sp, color = Color.Gray)
            Spacer(modifier = Modifier.weight(1f))
            Icon(Icons.Default.BookmarkBorder, contentDescription = null, tint = Color.Gray)
        }

        Divider(modifier = Modifier.padding(top = 16.dp), color = Color.LightGray.copy(alpha = 0.3f))


    }


}