package br.com.fiap.aeon.screens

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController


@Composable
fun LoginScreen(navController: NavController) {
    var email by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }
    val context = LocalContext.current
    val sharedPref = context.getSharedPreferences("AeonPrefs", Context.MODE_PRIVATE)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(32.dp)
    ) {
        Text(
            text = "AEON LOGIN",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFFFBFF31), // Seu YellowAeon
            modifier = Modifier.align(Alignment.TopCenter).padding(top = 40.dp)
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center)
        ) {
            TextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))


            TextField(
                value = senha,
                onValueChange = { senha = it },
                label = { Text("Senha") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(32.dp))


            Button(
                onClick = {
                    // Se o email não estiver vazio, deixa passar
                    if (email.isNotEmpty()) {
                        navController.navigate("menu")
                    }
                    sharedPref.edit().putString("usuario_nome", email).apply()

                    navController.navigate("menu")
                },
                colors = ButtonDefaults.buttonColors(Color(0xFF750D8F)),
                modifier = Modifier.size(width = 200.dp, height = 48.dp)
            ) {
                Text(
                    text = "Acessar",
                    fontSize = 20.sp,
                    color = Color.White
                )
            }

            Spacer(modifier = Modifier.height(16.dp))


            Text(
                text = "Esqueci minha senha",
                color = Color.White,
                fontSize = 14.sp,
                modifier = Modifier.clickable { /* Ação futura */ }
            )
        }
    }
}