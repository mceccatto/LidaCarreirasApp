package br.dev.codelabs.lidacarreiras.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import br.dev.codelabs.lidacarreiras.data.model.Vaga
import kotlinx.coroutines.flow.Flow

@Dao
interface VagaDao {
    @Query("SELECT * FROM vagas")
    fun listar(): Flow<List<Vaga>>

    @Insert
    suspend fun inserir(vaga: Vaga)

    @Update
    suspend fun atualizar(vaga: Vaga)

    @Delete
    suspend fun excluir(vaga: Vaga)

    @Query("DELETE FROM vagas WHERE empresaId = :empresaId AND dataCadastro = :dataCadastro")
    suspend fun excluir(empresaId: Int, dataCadastro: String)

    @Query("DELETE FROM vagas")
    suspend fun excluirTodos()
}