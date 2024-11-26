package com.example.gerenciamentodejogos.model.database

import android.content.Context
import android.util.Log
import com.example.gerenciamentodejogos.model.database.dao.JogoDao
import com.example.gerenciamentodejogos.model.entity.Jogo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class JogoRepository(context: Context) {

    private var jogoDao: JogoDao

    // Inicializando o banco de dados e o DAO
    private val database = AppDataBase.getDatabase(context)

    init {
        jogoDao = database.jogoDao()
    }

    suspend fun buscarTodos(): List<Jogo> = withContext(Dispatchers.IO) {
        jogoDao.buscarTodos()
    }

    suspend fun salvarJogo(jogo: Jogo) = withContext(Dispatchers.IO) {
        try {
            jogoDao.salvarJogo(jogo)
        } catch (e: Exception) {
            Log.e("Erro DB", "Erro ao acessar o db: ${e.message}")
        }
    }

    suspend fun excluirJogo(jogo: Jogo) = withContext(Dispatchers.IO) {
        jogoDao.excluirJogo(jogo)
    }

    suspend fun buscarPorTitulo(titulo: String): List<Jogo> = withContext(Dispatchers.IO) {
        jogoDao.buscarPorTitulo(titulo)
    }
}