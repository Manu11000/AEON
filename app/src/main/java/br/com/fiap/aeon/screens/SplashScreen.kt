package com.aeon.app.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {

    LaunchedEffect(key1 = true) {
        delay(2500) // Espera 2,5 segundos (2500 milisegundos)

        // Vai para o login e remove a splash da pilha
        navController.navigate("login") {
            popUpTo("splash") { inclusive = true }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black), // Fundo preto AEON
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "AEON",
                fontSize = 48.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFFBFF31) // YellowAeon
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Urban Experience",
                fontSize = 16.sp,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(32.dp))


            CircularProgressIndicator(
                color = Color(0xFF750D8F),
                strokeWidth = 4.dp
            )
        }
    }
}