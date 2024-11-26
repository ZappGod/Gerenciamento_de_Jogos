package com.example.gerenciamentodejogos.viewmodel

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gerenciamentodejogos.data.database.JogoRepository
import com.example.gerenciamentodejogos.data.models.Jogo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class JogoListViewModel(application: Application) : AndroidViewModel(application)  {

    // A lista de jogos, inicializada como uma lista vazia
    private val jogoRepository = JogoRepository(application)
    var jogos by mutableStateOf<List<Jogo>>(emptyList())

    // Função para carregar todos os jogos do banco de dados
    fun carregarJogos() {
        // Usando viewModelScope para carregar os jogos em background
        viewModelScope.launch(Dispatchers.IO) {
            val listaJogos = jogoRepository.buscarTodos()  // Chama o método do repositório
            // Atualiza a lista de jogos na UI (comunicando com a thread principal)
            withContext(Dispatchers.Main) {
                jogos = listaJogos
            }
        }
    }

    // Função de busca por título
    fun buscarJogosPorTitulo(titulo: String) {
        if (titulo.isBlank()) {
            viewModelScope.launch(Dispatchers.IO) {
                val listaJogos = jogoRepository.buscarTodos()  // Chama o método do repositório
                // Atualiza a lista de jogos na UI (comunicando com a thread principal)
                withContext(Dispatchers.Main) {
                    jogos = listaJogos
                }
            }
        } else {
            viewModelScope.launch(Dispatchers.IO) {
                val jogosFiltradosList = jogoRepository.buscarPorTitulo(titulo)
                withContext(Dispatchers.Main) {
                    jogos = jogosFiltradosList
                }
            }
        }
    }
}
