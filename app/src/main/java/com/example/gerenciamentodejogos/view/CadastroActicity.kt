package com.example.gerenciamentodejogos.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gerenciamentodejogos.viewmodel.CadastroViewModel

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

    BorderedBox() {
        CenteredColumn() {

                Text("Cadastro de Jogo", fontSize = 24.sp)
                Spacer(modifier = Modifier.height(16.dp))

                TextField(
                    value = viewModel.titulo,
                    onValueChange = { viewModel.titulo = it },
                    label = { Text("Título do Jogo") })
                Spacer(modifier = Modifier.height(8.dp))

                TextField(
                    value = viewModel.categoria,
                    onValueChange = { viewModel.categoria = it },
                    label = { Text("Categoria") })
                Spacer(modifier = Modifier.height(8.dp))

                TextField(
                    value = viewModel.plataforma,
                    onValueChange = { viewModel.plataforma = it },
                    label = { Text("Plataforma") })
                Spacer(modifier = Modifier.height(16.dp))

                Button(onClick = {
                    viewModel.salvarJogo(context)  // Chamando a função da ViewModel
                }) {
                    Text("Salvar Jogo")
                }

        }
    }
}

