package com.aeon.app.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.StarOutline
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReviewScreen(navController: NavController) {
    var reviewText by remember { mutableStateOf("") }
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF0FFF5)) // Fundo verde-água do app
            .verticalScroll(scrollState)
            .padding(20.dp)
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(Icons.Default.KeyboardArrowLeft, contentDescription = "Voltar", modifier = Modifier.size(32.dp))
            }
            Text("Review", fontSize = 22.sp, fontWeight = FontWeight.Bold)

            Box(
                modifier = Modifier
                    .size(32.dp)
                    .clip(CircleShape)
                    .background(Color.DarkGray)
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(Color(0xFFFFD600).copy(alpha = 0.5f), Color(0xFF750D8F).copy(alpha = 0.5f))
                    )
                )
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text("BAR TAN TAN", fontSize = 24.sp, fontWeight = FontWeight.ExtraBold, letterSpacing = 1.sp)
        Spacer(modifier = Modifier.height(8.dp))

        Row {
            repeat(5) {
                Icon(
                    imageVector = Icons.Outlined.StarOutline,
                    contentDescription = "Estrela",
                    modifier = Modifier.size(40.dp).padding(end = 4.dp),
                    tint = Color.Gray
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = reviewText,
            onValueChange = { reviewText = it },
            placeholder = { Text("O que achou da sua experiência?", color = Color.Gray) },
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp),
            shape = RoundedCornerShape(16.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                focusedBorderColor = Color(0xFF750D8F), // Borda roxa ao clicar
                unfocusedBorderColor = Color.Transparent // Sem borda quando não está clicado
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        //Botão para adicionar fotos
        Button(
            onClick = { /* Ação galeria */ },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFA663B5)),
            shape = RoundedCornerShape(50)
        ) {
            Icon(Icons.Default.PhotoCamera, contentDescription = null, tint = Color.White)
            Spacer(modifier = Modifier.width(8.dp))
            Text("Adicionar Fotos e Vídeos", color = Color.White)
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text("Quer adicionar mais detalhes?", fontSize = 14.sp, color = Color.Gray)
        Spacer(modifier = Modifier.height(8.dp))

        DropdownAeon("Valor Gasto")
        DropdownAeon("Tempo de Espera")
        DropdownAeon("Acessibilidade")
        DropdownAeon("Adequado para crianças")

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = {
                // Futuramente envia pra API e volta pro feed
                navController.popBackStack()
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF750D8F)),
            shape = RoundedCornerShape(50)
        ) {
            Text("Publicar", color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(100.dp))
    }
}
@Composable
fun DropdownAeon(text: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .background(Color(0xFFE0E0E0), RoundedCornerShape(8.dp))
            .clickable { /* Abre o menu */ }
            .padding(horizontal = 16.dp, vertical = 14.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = text, color = Color.DarkGray, fontSize = 14.sp)
        Icon(Icons.Default.KeyboardArrowDown, contentDescription = null, tint = Color.Gray)
    }
}