package com.example.gerenciamentodejogos.model.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.gerenciamentodejogos.model.entity.Jogo

@Dao
interface JogoDao {

    @Insert
    suspend fun salvarJogo(jogo: Jogo)

    @Delete
    suspend fun excluirJogo(jogo: Jogo)

    @Query("SELECT * FROM jogos")
    suspend fun buscarTodos(): List<Jogo>

    @Query("SELECT * FROM jogos WHERE titulo LIKE :titulo")
    suspend fun buscarPorTitulo(titulo: String): List<Jogo>

    @Query("SELECT * FROM jogos WHERE id = :id")
    suspend fun getById(id: Int): Jogo
}