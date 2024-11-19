package com.example.gerenciamentodejogos.viewmodel

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.Room
import com.example.gerenciamentodejogos.model.Jogo

@Database(entities = [Jogo::class], version = 1)
abstract class AppDataBase : RoomDatabase() {

    abstract fun jogoDao() : JogoDao

    companion object{
        private var INSTANCE : AppDataBase? = null
        fun getDatabase(context : Context) : AppDataBase{
            return INSTANCE ?: synchronized(this){
                val tempInstance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDataBase::class.java,
                    "jogos_db"
                ).build()
                INSTANCE = tempInstance
                tempInstance
            }
        }
    }

}