package com.example.gerenciamentodejogos.view

import android.icu.lang.UCharacter.VerticalOrientation
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.gerenciamentodejogos.model.database.AppDataBase
import com.example.gerenciamentodejogos.model.entity.Jogo
import com.example.gerenciamentodejogos.viewmodel.DetailsViewModel
import com.example.gerenciamentodejogos.viewmodel.JogoListViewModel

@Composable
fun DetailsLayout(viewModel: DetailsViewModel = viewModel(), navController: NavController, uid:Int){
    val context = LocalContext.current

    LaunchedEffect(uid) {
        viewModel.loadDetails(uid)
    }

    // Observa a lista de jogos da ViewModel
    val detalheJogo = viewModel.details

    var showUpdateDialog by remember { mutableStateOf(false) }
    var showDeleteDialog by remember { mutableStateOf(false) }

    if(showUpdateDialog){
        detalheJogo?.let {
            UpdateGameDialog(
                jogo = it,
                onConfirm = { titulo, categoria, plataforma ->
                    viewModel.updateGame(Jogo(detalheJogo.id, titulo, categoria, plataforma))
                    showUpdateDialog = false

                    Toast.makeText(
                        context,
                        "Jogo editado com sucesso!",
                        Toast.LENGTH_SHORT
                    ).show()

                },
                onDismiss = {
                    showUpdateDialog = false
                }
            )
        }
    }

    if(showDeleteDialog){
        DeleteGameDialog(
            onConfirm = {
                if (detalheJogo != null) {
                    viewModel.deleteGame(detalheJogo)
                }
                showDeleteDialog = false

                Toast.makeText(
                    context,
                    "Jogo deletado com sucesso!",
                    Toast.LENGTH_SHORT
                ).show()

                navController.navigate("jogoList")
            },
            onDismiss = {
                showDeleteDialog = false
            }
        )
    }

    CenteredColumn (
        Modifier
            .fillMaxSize()
            .padding(20.dp)){
        Text("Detalhe do Jogo", fontSize = 25.sp, fontWeight = FontWeight.Medium)
        Spacer(modifier = Modifier.height(32.dp))

        Text("Nome: ${detalheJogo?.titulo}")
        Spacer(modifier = Modifier.height(15.dp))

        Text("Categoria: ${detalheJogo?.categoria}")
        Spacer(modifier = Modifier.height(15.dp))

        Text("Plataforma: ${detalheJogo?.plataforma}")
        Spacer(modifier = Modifier.height(15.dp))

        Row (verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceAround, modifier = Modifier.fillMaxWidth()) {

            OutlinedIconButton(shape = CircleShape, border = BorderStroke(1.dp, Color.Cyan), onClick = {
                showUpdateDialog = true
            }) {
                Icon(
                    Icons.Default.Edit, contentDescription = "Editar Jooj", tint = Color.LightGray
                )
            }

            OutlinedIconButton(shape = CircleShape, border = BorderStroke(1.dp, Color.Cyan), onClick = {
                showDeleteDialog = true
            }) {
                Icon(
                    Icons.Default.Delete, contentDescription = "Deletar Jorge", tint = Color.LightGray
                )
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeleteGameDialog(onConfirm: () -> Unit, onDismiss: () -> Unit){
    BasicAlertDialog(
        onDismissRequest = onDismiss,
    ){
        Surface(
            modifier = Modifier
                .wrapContentWidth()
                .wrapContentHeight()
                .padding(8.dp),
            shape = RoundedCornerShape(20.dp),
            shadowElevation = 2.dp,
            tonalElevation = AlertDialogDefaults.TonalElevation
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Tem certeza que quer deletar esse jogo?",
                )
                Spacer(modifier = Modifier.height(16.dp))

                Row (verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceAround, modifier = Modifier.fillMaxWidth()) {
                    TextButton(
                        onClick = onDismiss,
                    ) {
                        Text("Cancelar")
                    }
                    TextButton(
                        onClick = onConfirm,
                    ) {
                        Text("Deletar")
                    }
                }

            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateGameDialog(jogo:Jogo, onConfirm: (titulo:String, categoria:String, plataforma:String) -> Unit, onDismiss: () -> Unit){
    val (_, titulo, categoria, plataforma) = jogo

    var newTitle by remember { mutableStateOf(titulo) }
    var newCategory by remember { mutableStateOf(categoria) }
    var newPlataform by remember { mutableStateOf(plataforma) }

    BasicAlertDialog(
        onDismissRequest = onDismiss,
    ){
        Surface(
            modifier = Modifier
                .wrapContentWidth()
                .wrapContentHeight()
                .padding(8.dp),
            shape = RoundedCornerShape(20.dp),
            shadowElevation = 2.dp,
            tonalElevation = AlertDialogDefaults.TonalElevation
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Editar jogo",
                )

                Spacer(modifier = Modifier.height(16.dp))
                TextField(
                    value = newTitle,
                    onValueChange = { newTitle = it },
                    label = { Text("Título") }
                )

                Spacer(modifier = Modifier.height(16.dp))
                TextField(
                    value = newCategory,
                    onValueChange = { newCategory = it },
                    label = { Text("Categoria") }
                )

                Spacer(modifier = Modifier.height(16.dp))
                TextField(
                    value = newPlataform,
                    onValueChange = { newPlataform = it },
                    label = { Text("Plataforma") }
                )


                Row (verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceAround, modifier = Modifier.fillMaxWidth()) {
                    TextButton(
                        onClick = onDismiss,
                    ) {
                        Text("Cancelar")
                    }
                    TextButton(
                        onClick = { onConfirm(newTitle, newCategory, newPlataform) },
                    ) {
                        Text("Salvar")
                    }
                }

            }
        }
    }
}