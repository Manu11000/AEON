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
import br.com.fiap.aeon.screens.MapsScreen
import br.com.fiap.aeon.screens.MenuScreen
import com.aeon.app.screens.FavoritosScreen
import com.aeon.app.screens.PerfilScreen
import com.aeon.app.screens.PostDetailScreen
import com.aeon.app.screens.ReviewScreen
import com.aeon.app.screens.SplashScreen
import br.com.fiap.aeon.ui.components.AeonBottomBar
import br.com.fiap.aeon.ui.theme.AEON2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AEON2Theme {
                val navController = rememberNavController()


                Scaffold(modifier = Modifier.fillMaxSize(),
                    bottomBar = {

                        AeonBottomBar(navController = navController)
                    }

                ) { innerPadding ->

                    NavHost(
                        navController = navController,
                        startDestination = "splash",
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable("splash") {
                            SplashScreen(navController)
                        }
                        composable("login") {
                            LoginScreen(navController)
                        }
//                        composable("cadastro") {
//                            CadastroScreen(navController)
//                        }
                        composable("menu") {
                            // Aqui você passa o motor para a tela de Menu (Feed)
                            MenuScreen(navController)
                        }
                        composable("map") {
                            MapsScreen(navController)
                        }
                        composable("post_detail") {
                            PostDetailScreen(navController = navController)
                        }
                        composable("review") {
                            ReviewScreen(navController) }

                        composable("perfil") {
                            PerfilScreen(navController)
                        }
                        composable("favoritos") {
                            FavoritosScreen(navController) }
                    }
                }
            }
        }
    }
}
