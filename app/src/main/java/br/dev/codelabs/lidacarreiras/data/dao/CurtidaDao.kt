package br.dev.codelabs.lidacarreiras.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import br.dev.codelabs.lidacarreiras.data.model.Curtida
import kotlinx.coroutines.flow.Flow

@Dao
interface CurtidaDao {
    @Query("SELECT * FROM curtidas")
    fun listar(): Flow<List<Curtida>>

    @Query("SELECT * FROM curtidas WHERE vagaId = :vagaId AND candidatoId = :candidatoId")
    fun buscaId(vagaId: String, candidatoId: String): Array<Curtida>

    @Insert
    suspend fun inserir(curtida: Curtida)

    @Insert
    suspend fun curtir(curtida: Curtida)

    @Delete
    suspend fun descurtir(curtida: Curtida)

    @Delete
    suspend fun excluir(curtida: Curtida)
}