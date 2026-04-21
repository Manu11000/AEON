package br.com.fiap.aeon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.fiap.aeon.screens.`LoginScreen.kt`
import br.com.fiap.aeon.screens.MenuScreen
import br.com.fiap.aeon.ui.theme.AEONTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AEONTheme {
                val navController = rememberNavController()

                // O Scaffold dá o espaço para o conteúdo não bater na barra do sistema
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = "login"
                    ) {
                        composable("login") {
                            // Aqui você passa o motor para a tela de Login
                            `LoginScreen.kt`(modifier = Modifier.padding(innerPadding), navController)
                        }
                        composable("menu") {
                            // Aqui você passa o motor para a tela de Menu (Feed)
                            MenuScreen(modifier = Modifier.padding(innerPadding), navController)
                        }
                    }
                }
            }
        }
    }
}
