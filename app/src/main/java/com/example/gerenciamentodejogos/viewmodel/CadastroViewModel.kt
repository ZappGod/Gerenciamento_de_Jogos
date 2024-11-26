package com.example.gerenciamentodejogos.viewmodel

import android.app.Application
import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.gerenciamentodejogos.data.database.JogoRepository
import com.example.gerenciamentodejogos.data.models.Jogo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CadastroViewModel(application: Application) : AndroidViewModel(application) {

    private val jogoRepository = JogoRepository(application)

    // Estado dos campos de entrada
    var titulo by mutableStateOf("")
    var categoria by mutableStateOf("")
    var plataforma by mutableStateOf("")

    // Função para salvar o jogo
    fun salvarJogo(context: Context) {
        if (titulo.isNotBlank() && categoria.isNotBlank() && plataforma.isNotBlank()) {
            // Rodando em um thread de background
            viewModelScope.launch(Dispatchers.IO) {
                jogoRepository.salvarJogo(Jogo(0, titulo, categoria, plataforma))
                // Pós operação (pode ser um LiveData para UI reagir)
                withContext(Dispatchers.Main) {
                    Toast.makeText(context, "Jogo cadastrado com sucesso!", Toast.LENGTH_SHORT).show()
                }

                // Limpeza dos campos
                titulo = ""
                categoria = ""
                plataforma = ""
            }
        } else {
            Toast.makeText(context, "Preencha todos os campos!", Toast.LENGTH_SHORT).show()
        }
    }
}
