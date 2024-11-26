package com.example.gerenciamentodejogos.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.gerenciamentodejogos.data.models.Jogo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Jogo::class], version = 1, exportSchema = false)
abstract class AppDataBase : RoomDatabase() {

    abstract fun jogoDao(): JogoDao

    companion object {
        @Volatile
        private var INSTANCE: AppDataBase? = null

        // Obtém a instância do banco de dados (Singleton)
        @Synchronized
        fun getDatabase(context: Context): AppDataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDataBase::class.java,
                    "jogos_database"
                )
                    .fallbackToDestructiveMigration() // Apaga e recria o banco se houver mudança de versão
                    .addCallback(roomCallback)       // Callback para ações ao criar o banco
                    .build()
                INSTANCE = instance
                instance
            }
        }

        // Callback para inicialização
        private val roomCallback = object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                populateDatabase(INSTANCE!!)
            }
        }

        // Popula o banco de dados com dados iniciais
        private fun populateDatabase(database: AppDataBase) {
            val jogoDao = database.jogoDao()
            CoroutineScope(Dispatchers.IO).launch {
                jogoDao.salvarJogo(Jogo(0, "Jogo Exemplo 1", "Categoria 1", "Plataforma 1"))
                jogoDao.salvarJogo(Jogo(0, "Jogo Exemplo 2", "Categoria 2", "Plataforma 2"))
                jogoDao.salvarJogo(Jogo(0, "Jogo Exemplo 3", "Categoria 3", "Plataforma 3"))
            }
        }
    }
}
