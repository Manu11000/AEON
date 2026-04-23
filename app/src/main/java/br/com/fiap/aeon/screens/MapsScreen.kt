package br.com.fiap.aeon.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.runtime.Composable
import br.com.fiap.aeon.components.FilterChipCustom


@Composable
fun MapsScreen(modifier: Modifier = Modifier, navController: NavController) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFDDE5DE)) // fundo claro padrão
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = "<",
                fontSize = 20.sp,
                modifier = Modifier
                    .clickable { navController.popBackStack() }
            )

            Spacer(modifier = Modifier.width(12.dp))

            Text(
                text = "Explorar Lugares",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }

        // busca
        OutlinedTextField(
            value = "",
            onValueChange = {},
            placeholder = { Text("Pesquise por lugares") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Teste para escolher(apenas visual)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(350.dp)
                .padding(horizontal = 16.dp)
                .background(Color.LightGray, RoundedCornerShape(16.dp)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "MAPA",
                color = Color.DarkGray,
                fontSize = 16.sp
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        // filtros
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            FilterChipCustom("Seguro")
            FilterChipCustom("Barato")
            FilterChipCustom("Sem fila")
        }

        Spacer(modifier = Modifier.height(20.dp))

        // botão
        Button(
            onClick = { navController.navigate("menu") },
            colors = ButtonDefaults.buttonColors(Color(0xFF750D8F)),
            shape = RoundedCornerShape(50),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp)
                .height(50.dp)
        ) {
            Text(
                text = "Ir Agora",
                color = Color.White,
                fontSize = 16.sp
            )
        }

        Spacer(modifier = Modifier.weight(1f))


    }
}