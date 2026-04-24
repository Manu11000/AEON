package br.com.fiap.aeon.screens

import androidx.compose.material.*
import androidx.compose.material3.Text
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import br.com.fiap.aeon.ui.components.AeonBottomBar
import br.com.fiap.aeon.ui.theme.YellowAeon
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class) // BottomSheet ainda é experimental no Material 2
@Composable
fun MapsScreen(navController: NavController) {
    val sheetState = rememberBottomSheetState(initialValue = BottomSheetValue.Collapsed)

    val scaffoldState = androidx.compose.material.rememberBottomSheetScaffoldState(
        bottomSheetState = sheetState
    )
    val scope = rememberCoroutineScope()

    androidx.compose.material.BottomSheetScaffold(
        scaffoldState = scaffoldState,

        sheetContent = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .defaultMinSize(minHeight = 1.dp) // Garante que ela existe para o toque
                    .padding(16.dp)
            ){
                BottomSheetContent(scope, sheetState)
            }


        },

        sheetPeekHeight = 100.dp,
        sheetShape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
        sheetBackgroundColor = Color.White,
        sheetElevation = 16.dp,

        content = {

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFF1A1F2C))
            ) {
                //barra de busca fixa no topo do mapa
                MapTopBar()


                Text("MAPA (DARK MODE) AQUI", modifier = Modifier.align(Alignment.Center), color = Color.Gray)

                Box(
                    modifier = Modifier.align(Alignment.BottomCenter)
                ) {
                    AeonBottomBar(navController = navController)
                }
            }
        },


        )
}

@Composable
fun MapTopBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 24.dp)
            .background(Color.Black, RoundedCornerShape(24.dp))
            .padding(horizontal = 20.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text("Pesquisar...", color = Color.Gray, fontSize = 16.sp)
        Icon(imageVector = Icons.Default.Search, contentDescription = "Buscar", tint = Color.Gray)
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BottomSheetContent(scope: CoroutineScope, sheetState: BottomSheetState) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .animateContentSize()
    ) {

        Box(
            modifier = Modifier
                .size(width = 40.dp, height = 4.dp)
                .background(Color.LightGray, RoundedCornerShape(2.dp))
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 12.dp)
        )


        Row(
            modifier = Modifier.fillMaxWidth().clickable {
                // Permite clicar para expandir/recolher
                scope.launch { if(sheetState.isCollapsed) sheetState.expand() else sheetState.collapse() }
            },
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Perdizes", // Localização vinda do GPS (Permissões)
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            // Dados do Clima - Vindo de API futuramente
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(imageVector = Icons.Default.Cloud, contentDescription = "Clima", tint = Color.Gray, modifier = Modifier.size(24.dp))
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = "23°", fontSize = 20.sp, color = Color.Black)
            }
        }

        if (sheetState.isExpanded) {
            Spacer(modifier = Modifier.height(24.dp))
            RecommendationsSection()
        }
    }
}

@Composable
fun RecommendationsSection() {
    Column {
        Text("Hora do Almoço", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = Color.Black)
        Spacer(modifier = Modifier.height(12.dp))


        LazyRow(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            items(2) {
                PlaceCard(title = "Bar TanTan", rating = "4,5")
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
        Text("Para Explorar", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = Color.Black)
        Spacer(modifier = Modifier.height(12.dp))
        // Replicar o mesmo LazyRow abaixo se necessário
    }
}

@Composable
fun PlaceCard(title: String, rating: String) {
    Card(
        modifier = Modifier.size(width = 240.dp, height = 280.dp),
        colors = CardDefaults.cardColors(containerColor = YellowAeon),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Placeholder para Imagem
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp)
                    .background(Color.Transparent, RoundedCornerShape(8.dp)),
                contentAlignment = Alignment.Center
            ) {

                Icon(imageVector = Icons.Default.Image, contentDescription = null, tint = Color.Gray, modifier = Modifier.size(64.dp))
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(text = title, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = Color.Black)

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = rating, color = Color.Black, fontSize = 14.sp)
                Icon(imageVector = Icons.Default.Star, contentDescription = null, tint = Color(0xFFFFD600), modifier = Modifier.size(16.dp))
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = "(150+) R$50-55", color = Color.Gray, fontSize = 12.sp)
            }
            Text(text = "Abre às 10:00   500m", color = Color.Gray, fontSize = 12.sp)
        }
    }
}