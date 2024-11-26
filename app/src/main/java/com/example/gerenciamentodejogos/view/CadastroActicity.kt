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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gerenciamentodejogos.data.database.JogoRepository
import com.example.gerenciamentodejogos.data.models.Jogo
import com.example.gerenciamentodejogos.viewmodel.CadastroViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CadastroActicity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            CadastroScreen()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CadastroScreen(viewModel: CadastroViewModel = viewModel()) {

    val context = LocalContext.current

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Cadastro de Jogo", fontSize = 24.sp)

        Spacer(modifier = Modifier.height(16.dp))

        TextField(value = viewModel.titulo, onValueChange = { viewModel.titulo = it }, label = { Text("Título do Jogo") })
        Spacer(modifier = Modifier.height(8.dp))
        TextField(value = viewModel.categoria, onValueChange = { viewModel.categoria = it }, label = { Text("Categoria") })
        Spacer(modifier = Modifier.height(8.dp))
        TextField(value = viewModel.plataforma, onValueChange = { viewModel.plataforma = it }, label = { Text("Plataforma") })

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            viewModel.salvarJogo(context)  // Chamando a função da ViewModel
        }) {
            Text("Salvar Jogo")
        }
    }
}

