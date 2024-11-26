package com.example.gerenciamentodejogos.viewmodel

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.gerenciamentodejogos.model.Jogo

@Dao
interface JogoDao {

    @Insert
    suspend fun salvarJogo(jogo: Jogo)

    @Query("SELECT * FROM jogos")
    suspend fun buscarTodos() : List<Jogo>

    @Query("SELECT * FROM jogos WHERE jogos.id = :id")
    suspend fun getById(id: Int): Jogo
    
}