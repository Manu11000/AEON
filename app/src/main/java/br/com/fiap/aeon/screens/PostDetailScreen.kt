package com.aeon.app.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PostDetailScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "ÆON",
                fontSize = 32.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color.White,
                letterSpacing = 4.sp
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            val pagerState = rememberPagerState(pageCount = { 5 })

            HorizontalPager(
                state = pagerState,
                modifier = Modifier.fillMaxSize()
            ) { page ->
                // Placeholder da Foto
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color(0xFF2C2C2C)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(Icons.Default.Image, contentDescription = null, tint = Color.Gray, modifier = Modifier.size(80.dp))
                }
            }

            IconButton(
                onClick = { navController.popBackStack() }, // Volta para o Feed
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(8.dp)
            ) {
                Icon(Icons.Default.KeyboardArrowLeft, contentDescription = "Voltar", tint = Color.White, modifier = Modifier.size(32.dp))
            }

            Row(
                Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 16.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                repeat(5) { iteration ->
                    val color = if (pagerState.currentPage == iteration) Color.White else Color.Gray.copy(alpha = 0.5f)
                    Box(
                        modifier = Modifier
                            .padding(horizontal = 4.dp)
                            .clip(CircleShape)
                            .background(color)
                            .size(8.dp)
                    )
                }
            }
        }


        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFF0FFF5)) // Verde-água claro do seu protótipo
                .padding(16.dp)
        ) {

            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(modifier = Modifier.size(45.dp).clip(CircleShape).background(Color.Gray))
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text("Draculaura", fontWeight = FontWeight.Bold, color = Color.Black)
                    Text("6 Avaliações . 13 Fotos", fontSize = 12.sp, color = Color.Gray)
                }
                Spacer(modifier = Modifier.weight(1f))
                Icon(Icons.Default.MoreHoriz, contentDescription = null, tint = Color.Gray)
            }

            // Estrelas
            Row(modifier = Modifier.padding(vertical = 4.dp), verticalAlignment = Alignment.CenterVertically) {
                repeat(4) { Icon(Icons.Default.Star, null, tint = Color(0xFFFFD600), modifier = Modifier.size(16.dp)) }
                Icon(Icons.Default.Star, null, tint = Color.Gray, modifier = Modifier.size(16.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Text("5 Meses Atrás", fontSize = 10.sp, color = Color.Gray)
            }

            Text(
                text = "Hoje fui conhecer um marco histórico da nossa cidade de São Paulo - Catedral Metropolitana...",
                fontSize = 14.sp,
                color = Color.Black,
                modifier = Modifier.padding(vertical = 8.dp)
            )

            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(top = 8.dp)) {
                Icon(Icons.Default.Favorite, contentDescription = null, tint = Color.Red, modifier = Modifier.size(20.dp))
                Text(" 32 Curtidas", fontSize = 12.sp, color = Color.Gray)
                Spacer(modifier = Modifier.weight(1f))
                Icon(Icons.Default.BookmarkBorder, contentDescription = null, tint = Color.Gray)
            }

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}