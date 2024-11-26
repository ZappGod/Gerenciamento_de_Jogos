package com.example.gerenciamentodejogos.viewmodel

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.gerenciamentodejogos.model.database.JogoRepository
import com.example.gerenciamentodejogos.model.entity.Jogo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailsViewModel(application: Application): AndroidViewModel(application) {

    // A tela de detalhe inicializará com valor null
    private val jogoRepository = JogoRepository(application)
    var details by mutableStateOf<Jogo?>(null)

    //Função para carregar os detalhes do jogo
    fun loadDetails(id: Int){
        viewModelScope.launch(Dispatchers.IO) {
            val gameDetails = jogoRepository.getById(id)

            withContext(Dispatchers.Main) {
                details = gameDetails
            }
        }
    }


}