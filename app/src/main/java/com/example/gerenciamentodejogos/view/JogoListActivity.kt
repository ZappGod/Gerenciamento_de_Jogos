package com.example.gerenciamentodejogos.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.gerenciamentodejogos.model.entity.Jogo
import com.example.gerenciamentodejogos.viewmodel.JogoListViewModel

@Composable
fun JogoListScreen(viewModel: JogoListViewModel = viewModel(), navController: NavController) {

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

                // Barra de pesquisa
                var searchQuery by remember { mutableStateOf("") }

                TextField(
                    value = searchQuery,
                    onValueChange = {
                        searchQuery = it // Atualiza o valor enquanto digita
                    },
                    label = { Text("Buscar por título") },
                    modifier = Modifier.fillMaxWidth(),
                    trailingIcon = {
                        IconButton(onClick = {
                            viewModel.buscarJogosPorTitulo(searchQuery) // Aciona a busca ao clicar na lupa
                        }) {
                            Icon(Icons.Default.Search, contentDescription = "Buscar")
                        }
                    }
                )

                Spacer(modifier = Modifier.height(16.dp))


                // Exibe a lista de jogos
                LazyColumn {
                    items(jogos) { jogo ->
                        JogoItem(jogo = jogo, navController)
                    }
                }
            }
        }
    }
}

@Composable
fun JogoItem(jogo: Jogo, navController: NavController) {
    Card(modifier = Modifier.fillMaxWidth().padding(8.dp), onClick = {
        val uid = jogo.id
        navController.navigate("details/$uid")
    }) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Título: ${jogo.titulo}", fontSize = 18.sp)
            Text(text = "Categoria: ${jogo.categoria}", fontSize = 16.sp)
            Text(text = "Plataforma: ${jogo.plataforma}", fontSize = 14.sp)
        }
    }
}
