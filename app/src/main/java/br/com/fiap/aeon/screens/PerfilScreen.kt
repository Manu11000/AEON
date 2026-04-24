package com.aeon.app.screens

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import androidx.navigation.NavController


@Composable
fun PerfilScreen(navController: NavController) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF0FFF5))
            .verticalScroll(scrollState)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(280.dp),
            contentAlignment = Alignment.BottomCenter
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp)
                    .padding(horizontal = 20.dp),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(8.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 60.dp, start = 20.dp, end = 20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text("Blair Willows", fontSize = 22.sp, fontWeight = FontWeight.Bold)
                            Text("@blairwillows", fontSize = 14.sp, color = Color.Gray)
                        }
                        Row {
                            Icon(Icons.Default.Analytics, contentDescription = "Estatísticas", tint = Color.Black)
                            Spacer(modifier = Modifier.width(16.dp))
                            Icon(Icons.Default.Settings, contentDescription = "Configurações", tint = Color.Black)
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        ProfileStatItem("44", "Postagens")
                        ProfileStatItem("4437", "Seguidores")
                        ProfileStatItem("48", "Seguindo")
                    }
                }
            }

            Box(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 20.dp)
                    .size(100.dp)
                    .clip(CircleShape)
                    .border(4.dp, Color.White, CircleShape)
                    .background(Color.Gray) // Placeholder Barbie
            )
        }

        Column(modifier = Modifier.padding(20.dp)) {
            Text("Destaques", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(12.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                HighlightCircle(Icons.Default.Business)
                HighlightCircle(Icons.Default.TheaterComedy)
                HighlightCircle(Icons.Default.Park)
                HighlightCircle(Icons.Default.Add, isAdd = true)
            }
        }

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .padding(bottom = 100.dp),
            shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp),
            colors = CardDefaults.cardColors(containerColor = Color.LightGray.copy(alpha = 0.3f))
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                ReviewExpandableCard("Terraço Jardins", "Restaurante", "R$200-250", "5")
                ReviewExpandableCard("Bar Tan Tan", "Bares", "R$50-55", "4")
                ReviewExpandableCard("Exposição MIS", "Exposição", "R$20-25", "4.2")
            }
        }
    }
}

@Composable
fun ProfileStatItem(number: String, label: String) {
    Card(
        modifier = Modifier.size(width = 85.dp, height = 70.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFFDFD35)),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(number, fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Text(label, fontSize = 10.sp)
        }
    }
}

@Composable
fun HighlightCircle(icon: ImageVector, isAdd: Boolean = false) {
    Box(
        modifier = Modifier
            .size(55.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Color(0xFF750D8F)), // Roxo AEON
        contentAlignment = Alignment.Center
    ) {
        Icon(icon, contentDescription = null, tint = Color.White)
    }
}

@Composable
fun ReviewExpandableCard(title: String, type: String, price: String, rating: String) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { expanded = !expanded },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFEBEBEB)),
        border = BorderStroke(1.dp, Color.Black.copy(alpha = 0.1f))
    ) {
        Column(modifier = Modifier.padding(16.dp).animateContentSize()) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(title, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                    Row {
                        Text("$type  •  ", fontSize = 12.sp, color = Color.Gray)
                        Text(price, fontSize = 12.sp, color = Color.Gray)
                    }
                }
                Icon(
                    if (expanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                    contentDescription = null
                )
            }

            if (expanded) {
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    "O texto do riveiw, " +
                            "junto com um mini carrossel de fotos das experiências.",
                    fontSize = 14.sp
                )
            }

            Spacer(modifier = Modifier.height(8.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(rating, fontWeight = FontWeight.Bold)
                Icon(Icons.Default.Star, null, tint = Color(0xFFFFD600), modifier = Modifier.size(16.dp))
                Text("  2 semanas atrás", fontSize = 12.sp, color = Color.Gray)
            }
        }
    }
}