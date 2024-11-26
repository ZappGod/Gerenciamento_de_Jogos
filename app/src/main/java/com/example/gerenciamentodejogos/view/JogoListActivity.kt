package com.example.gerenciamentodejogos.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gerenciamentodejogos.data.models.Jogo
import com.example.gerenciamentodejogos.ui.theme.GerenciamentoDeJogosTheme
import com.example.gerenciamentodejogos.viewmodel.JogoListViewModel

class JogoListActivity  : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            JogoListScreen()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun JogoListScreen(viewModel: JogoListViewModel = viewModel()) {
    // Carregar os jogos quando a tela for criada
    LaunchedEffect(true) {
        viewModel.carregarJogos()
    }

    // Observa a lista de jogos da ViewModel
    val jogos = viewModel.jogos

    BorderedBox() {
        CenteredColumn() {
            Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
                Text("Lista de Jogos", fontSize = 24.sp)

                Spacer(modifier = Modifier.height(16.dp))

                // Exibe a lista de jogos
                LazyColumn {
                    items(jogos) { jogo ->
                        JogoItem(jogo = jogo)
                    }
                }
            }
        }
    }
}

@Composable
fun JogoItem(jogo: Jogo) {
    Card(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Título: ${jogo.titulo}", fontSize = 18.sp)
            Text(text = "Categoria: ${jogo.categoria}", fontSize = 16.sp)
            Text(text = "Plataforma: ${jogo.plataforma}", fontSize = 14.sp)
        }
    }
}
