package br.dev.codelabs.lidacarreiras.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import br.dev.codelabs.lidacarreiras.data.model.Seguir
import kotlinx.coroutines.flow.Flow

@Dao
interface SeguirDao {
    @Query("SELECT * FROM seguindo")
    fun listar(): Flow<List<Seguir>>

    @Insert
    suspend fun inserir(seguir: Seguir)

    @Delete
    suspend fun excluir(seguir: Seguir)
}