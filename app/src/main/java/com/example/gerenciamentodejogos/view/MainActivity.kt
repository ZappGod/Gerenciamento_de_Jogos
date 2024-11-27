package com.example.gerenciamentodejogos.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.gerenciamentodejogos.view.ui.theme.GerenciamentoDeJogosTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            GerenciamentoDeJogosTheme {
                // Definindo o NavHost com navegação para cada tela
                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = "main") {
                    composable("main") {
                        MainScreen(navController) // Tela principal
                    }
                    composable("jogoList") {
                        JogoListScreen(viewModel = viewModel(), navController) // Tela de listagem de jogos
                    }
                    composable("cadastro") {
                        CadastroScreen() // Tela de cadastro de jogos
                    }
                    composable("details/{uid}") { navBackStackEntry ->
                        val uid = navBackStackEntry.arguments?.getString("uid")

                        uid?.let { id ->
                            print("id: $id")
                            DetailsLayout(viewModel = viewModel(),navController, uid = id.toInt())
                        }
                    }
                }
            }
            }
        }
    }

@Composable
fun MainScreen(navController: NavController) {
    // Exemplo de conteúdo para a tela principal
    BorderedBox() {
        CenteredColumn() {
            Text("Bem-vindo ao Gerenciamento de Jogos")

            Spacer(modifier = Modifier.height(20.dp))

            Button(onClick = { navController.navigate("jogoList") }) {
                Text("Ir para Lista de Jogos")
            }

            Spacer(modifier = Modifier.height(20.dp))

            Button(onClick = { navController.navigate("cadastro") }) {
                Text("Ir para Cadastro de Jogo")
            }

        }
    }
}



@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    GerenciamentoDeJogosTheme {
        Greeting("Android")
    }
}

@Composable
fun CenteredColumn(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .then(modifier), // Permite adicionar modificadores adicionais
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        content()
    }
}

@Composable
fun BorderedBox(
    modifier: Modifier = Modifier,
    padding: Dp = 16.dp, // Definindo um espaçamento padrão para as bordas
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(padding) // Aplica o espaçamento em torno da Box
    ) {
        content()
    }
}