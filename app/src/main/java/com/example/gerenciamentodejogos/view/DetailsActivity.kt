package com.example.gerenciamentodejogos.view

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gerenciamentodejogos.model.Jogo
import com.example.gerenciamentodejogos.viewmodel.AppDataBase

class DetailsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DetailsLayout()
        }
    }
}

@Composable
fun DetailsLayout(){

    //Variaveis para o DataBase
    var detalheJogo by remember { mutableStateOf<Jogo?>(null) }
    val context = LocalContext.current
    val db = AppDataBase.getDatabase(context)
    val jogoDao = db.jogoDao()

    LaunchedEffect(Unit) {
        try {
            detalheJogo = jogoDao.getById(69)
        } catch (e : Exception) {
            Log.e("ERRO DB", "Erro ao acessar o db: ${e.message}")
        }
    }

    Column (
        Modifier
            .fillMaxSize()
            .padding(20.dp)){
        Text("Detalhe do Jogo", fontSize = 25.sp)
        Spacer(modifier = Modifier.height(15.dp))

        Text("Nome: ${detalheJogo?.titulo}")
        Spacer(modifier = Modifier.height(15.dp))

        Text("Categoria: ${detalheJogo?.categoria}")
        Spacer(modifier = Modifier.height(15.dp))

        Text("Plataforma: ${detalheJogo?.plataforma}")
        Spacer(modifier = Modifier.height(15.dp))

        OutlinedIconButton(shape = CircleShape, border = BorderStroke(5.dp, Color.Cyan), onClick = {
            Toast.makeText(
                context,
                "SEXO GARAI KKKKKKKKKKKKK",
                Toast.LENGTH_SHORT
            ).show()
        }) {
            Icon(
                Icons.Default.Edit, contentDescription = "Editar Jooj", tint = Color.LightGray
            )
        }

        OutlinedIconButton(shape = CircleShape, border = BorderStroke(5.dp, Color.Cyan), onClick = {
            Toast.makeText(
                context,
                "FORNICAÇÃO HOMOAFETIVA KaKaKaKaKaKaKa",
                Toast.LENGTH_SHORT
            ).show()
        }) {
            Icon(
                Icons.Default.Delete, contentDescription = "Deletar Jorge", tint = Color.LightGray
            )
        }


    }
}