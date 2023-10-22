package br.dev.codelabs.lidacarreiras.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import br.dev.codelabs.lidacarreiras.data.model.Experiencia
import kotlinx.coroutines.flow.Flow

@Dao
interface ExperienciaDao {
    @Query("SELECT * FROM experiencias")
    fun listar(): Flow<List<Experiencia>>

    @Insert
    suspend fun inserir(experiencia: Experiencia)

    @Update
    suspend fun atualizar(experiencia: Experiencia)

    @Delete
    suspend fun excluir(experiencia: Experiencia)

    @Query("DELETE FROM experiencias WHERE candidatoId = :candidatoId AND dataCadastro = :dataCadastro")
    suspend fun excluir(candidatoId: Int, dataCadastro: String)

    @Query("DELETE FROM experiencias")
    suspend fun excluirTodos()
}