package com.example.gerenciamentodejogos.view

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gerenciamentodejogos.model.Jogo
import com.example.gerenciamentodejogos.viewmodel.AppDataBase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CadastroActicity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CadastroLayout()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CadastroLayout(){

    //Variaveis para o cadastro dos jogos
    var titulo by remember { mutableStateOf("") }
    var categoria by remember { mutableStateOf("") }
    var plataforma by remember { mutableStateOf("") }

    //Variaveis para o DataBase
    var listarJogos by remember { mutableStateOf<List<Jogo>>(emptyList()) }
    val context = LocalContext.current
    val db = AppDataBase.getDatabase(context)
    val jogoDao = db.jogoDao()

    LaunchedEffect(Unit) {
        try {
            listarJogos = jogoDao.buscarTodos()
        } catch (e : Exception) {

            Log.e("ERRO DB", "Erro ao acessar o db: ${e.message}")
        }
    }

    Column(
        Modifier
            .fillMaxSize()
            .padding(20.dp)) {

        Text("Cadastro de Jogos", fontSize = 25.sp)

        Spacer(modifier = Modifier.height(15.dp))

        TextField(value = titulo, onValueChange = { titulo = it },
            label = { Text("Título do Jogo")},
            modifier = Modifier.fillMaxWidth())

        Spacer(modifier = Modifier.height(12.dp))

        TextField(value = categoria, onValueChange = { categoria = it },
            label = { Text("Categoria")},
            modifier = Modifier.fillMaxWidth())

        Spacer(modifier = Modifier.height(12.dp))

        TextField(value = plataforma, onValueChange = { plataforma = it },
            label = { Text("Plataforma do jogo")},
            modifier = Modifier.fillMaxWidth())

        Spacer(modifier = Modifier.height(10.dp))

        Button(onClick = {
            if(titulo.isNotBlank() &&
                categoria.isNotBlank() &&
                plataforma.isNotBlank()){
                try {
                    CoroutineScope(Dispatchers.IO).launch{
                        jogoDao.salvarJogo(
                            Jogo(
                                0,
                                titulo,
                                categoria,
                                plataforma
                            )
                        )

                        delay(500)
                        listarJogos = jogoDao.buscarTodos()
                        titulo = ""
                        categoria = ""
                        plataforma = ""
                    }
                } catch (e : Exception) {
                    Log.e("Erro ao Salvar", "Erro ao salvar: ${e.message}")
                }
            } else {

                Toast.makeText(
                    context,
                    "Preencha todos os campos",
                    Toast.LENGTH_LONG
                ).show()

            }
        }) {
            
        }
    }
}
