package com.example.gerenciamentodejogos.model

import androidx.room.PrimaryKey
import androidx.room.Entity

@Entity (tableName = "jogos")
data class Jogo(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var titulo: String,
    var categoria: String,
    var plataforma:String
)