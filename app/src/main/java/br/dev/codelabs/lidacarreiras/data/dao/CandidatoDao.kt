package br.dev.codelabs.lidacarreiras.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import br.dev.codelabs.lidacarreiras.data.model.Candidato
import kotlinx.coroutines.flow.Flow

@Dao
interface CandidatoDao {
    @Query("SELECT * FROM candidatos")
    fun listar(): Flow<List<Candidato>>

    @Insert
    suspend fun inserir(candidato: Candidato)

    @Update
    suspend fun atualizar(candidato: Candidato)

    @Delete
    suspend fun excluir(candidato: Candidato)

    @Query("DELETE FROM candidatos WHERE id = :id")
    suspend fun excluir(id: Int)

    @Query("DELETE FROM candidatos")
    suspend fun excluirTodos()
}