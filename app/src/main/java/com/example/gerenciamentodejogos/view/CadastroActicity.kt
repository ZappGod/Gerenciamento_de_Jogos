package com.example.gerenciamentodejogos.view

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import com.example.gerenciamentodejogos.data.database.JogoRepository
import com.example.gerenciamentodejogos.data.models.Jogo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CadastroActicity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)


        setContent {
            CadastroScreen()
            ListaJogosScreen()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CadastroScreen() {

    val context = LocalContext.current

    var jogoRepository = JogoRepository(context);


    var titulo by remember { mutableStateOf("") }
    var categoria by remember { mutableStateOf("") }
    var plataforma by remember { mutableStateOf("") }


    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Cadastro de Jogo", fontSize = 24.sp)

        Spacer(modifier = Modifier.height(16.dp))

        TextField(value = titulo, onValueChange = { titulo = it }, label = { Text("Título do Jogo") })
        Spacer(modifier = Modifier.height(8.dp))
        TextField(value = categoria, onValueChange = { categoria = it }, label = { Text("Categoria") })
        Spacer(modifier = Modifier.height(8.dp))
        TextField(value = plataforma, onValueChange = { plataforma = it }, label = { Text("Plataforma") })

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            if (titulo.isNotBlank() && categoria.isNotBlank() && plataforma.isNotBlank()) {

                CoroutineScope(Dispatchers.IO).launch {

                    jogoRepository.salvarJogo(Jogo(0, titulo, categoria, plataforma))
                }

                Toast.makeText(context, "Jogo cadastrado com sucesso!", Toast.LENGTH_SHORT).show()
                titulo = ""
                categoria = ""
                plataforma = ""
            } else {
                Toast.makeText(context, "Preencha todos os campos!", Toast.LENGTH_SHORT).show()
            }
        }) {
            Text("Salvar Jogo")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ListaJogosScreen() {

    val context = LocalContext.current

    var jogoRepository = JogoRepository(context);

    // Lista de jogos
    val jogos = remember { mutableStateOf(listOf<Jogo>()) }

    // Carregar os jogos ao compor a tela
    LaunchedEffect(true) {
        jogos.value = jogoRepository.buscarTodos()
    }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Lista de Jogos",
            modifier = Modifier.padding(bottom = 16.dp)
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(jogos.value) { jogo ->
                JogoItem(jogo = jogo)
            }
        }
    }
}

@Composable
fun JogoItem(jogo: Jogo) {
    // Componente para exibir cada item da lista
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = jogo.titulo,
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Categoria: ${jogo.categoria}",
            )
            Text(
                text = "Plataforma: ${jogo.plataforma}",
            )
        }
    }
}
