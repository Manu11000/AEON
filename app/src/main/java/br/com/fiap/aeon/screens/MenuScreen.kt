package br.com.fiap.aeon.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.fiap.aeon.components.BottomBar

@Composable
fun MenuScreen(modifier: Modifier = Modifier, navController: NavController) {

    var isLiked by remember { mutableStateOf(false) }

    Scaffold(
        bottomBar = {
            BottomBar(navController)
        }
    ) { innerPadding ->

        Column(
            modifier = modifier
                .fillMaxSize()
                .background(Color.Black)
                .padding(innerPadding)
                .padding(16.dp)
        ) {

            // 🔝 TÍTULO
            Text(
                text = "AEON FEED",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // 🔘 BOTÕES SUPERIORES
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(
                    onClick = { /* futuramente perfil */ },
                    colors = ButtonDefaults.buttonColors(Color.White),
                    modifier = Modifier.size(width = 100.dp, height = 40.dp)
                ) {
                    Text("Perfil", fontSize = 14.sp, color = Color.Blue)
                }

                Button(
                    onClick = { navController.navigate("login") },
                    colors = ButtonDefaults.buttonColors(Color.White),
                    modifier = Modifier.size(width = 100.dp, height = 40.dp)
                ) {
                    Text("Sair", fontSize = 14.sp, color = Color.Blue)
                }

                Button(
                    onClick = { navController.navigate("map") },
                    colors = ButtonDefaults.buttonColors(Color(0xFF750D8F)),
                    modifier = Modifier.size(width = 150.dp, height = 40.dp)
                ) {
                    Text("Abrir Mapa", color = Color.White)
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // 📜 FEED (SCROLL)
            LazyColumn(
                modifier = Modifier.weight(1f)
            ) {
                items(5) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Column(modifier = Modifier.padding(12.dp)) {

                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(150.dp)
                                    .background(Color.LightGray, RoundedCornerShape(8.dp)),
                                contentAlignment = Alignment.Center
                            ) {
                                Text("Foto da Experiência", color = Color.DarkGray)
                            }

                            Spacer(modifier = Modifier.height(8.dp))

                            Text(
                                text = "Incrível pôr do sol no centro da cidade! #AeonUrban",
                                fontWeight = FontWeight.Medium,
                                color = Color.Black
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {

                                Row {
                                    repeat(5) {
                                        Icon(
                                            imageVector = Icons.Default.Star,
                                            contentDescription = null,
                                            tint = Color(0xFFFFD600),
                                            modifier = Modifier.size(20.dp)
                                        )
                                    }
                                }

                                IconButton(onClick = { isLiked = !isLiked }) {
                                    Icon(
                                        imageVector = if (isLiked)
                                            Icons.Default.Favorite
                                        else
                                            Icons.Default.FavoriteBorder,
                                        contentDescription = "Curtir",
                                        tint = if (isLiked) Color.Red else Color.Gray
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}