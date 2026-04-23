package br.com.fiap.aeon.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.foundation.background
import androidx.compose.runtime.Composable



@Composable
fun BottomBar(navController: NavController) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Black)
            .padding(12.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {

        Text("🏠", fontSize = 20.sp,
            modifier = Modifier.clickable { navController.navigate("menu") })

        Text("📍", fontSize = 20.sp,
            modifier = Modifier.clickable { navController.navigate("map") })

        Text("❤️", fontSize = 20.sp,
            modifier = Modifier.clickable { })

        Text("👤", fontSize = 20.sp,
            modifier = Modifier.clickable { })
    }
}