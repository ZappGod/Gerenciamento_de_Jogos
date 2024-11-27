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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gerenciamentodejogos.model.database.AppDataBase
import com.example.gerenciamentodejogos.model.entity.Jogo
import com.example.gerenciamentodejogos.viewmodel.DetailsViewModel
import com.example.gerenciamentodejogos.viewmodel.JogoListViewModel

@Composable
fun DetailsLayout(viewModel: DetailsViewModel = viewModel(), uid:Int){
    val context = LocalContext.current

    LaunchedEffect(uid) {
        viewModel.loadDetails(uid)
    }

    // Observa a lista de jogos da ViewModel
    val detalheJogo = viewModel.details

    Column (
        Modifier
            .fillMaxSize()
            .padding(20.dp)){
        Text("Detalhe do Jogo: $uid", fontSize = 25.sp)
        Spacer(modifier = Modifier.height(15.dp))

        Text("Nome: ${detalheJogo?.titulo}")
        Spacer(modifier = Modifier.height(15.dp))

        Text("Categoria: ${detalheJogo?.categoria}")
        Spacer(modifier = Modifier.height(15.dp))

        Text("Plataforma: ${detalheJogo?.plataforma}")
        Spacer(modifier = Modifier.height(15.dp))

        OutlinedIconButton(shape = CircleShape, border = BorderStroke(1.dp, Color.Cyan), onClick = {
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

        OutlinedIconButton(shape = CircleShape, border = BorderStroke(1.dp, Color.Cyan), onClick = {
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