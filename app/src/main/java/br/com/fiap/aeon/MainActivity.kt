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
import br.com.fiap.aeon.screens.LoginScreen
import br.com.fiap.aeon.screens.MenuScreen
import br.com.fiap.aeon.ui.theme.AEON2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AEON2Theme {
                // O navController deve ser declarado AQUI, fora do Scaffold
                val navController = rememberNavController()

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = "login",
                        modifier = Modifier.padding(innerPadding) // Aplica o padding aqui
                    ) {
                        composable("login") {
                            LoginScreen(navController = navController)
                        }
                        composable("menu") {
                            MenuScreen(navController = navController)
                        }
                    }
                }
            }
        }
    }
}