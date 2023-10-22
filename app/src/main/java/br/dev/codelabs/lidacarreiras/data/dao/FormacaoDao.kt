package br.dev.codelabs.lidacarreiras.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import br.dev.codelabs.lidacarreiras.data.model.Formacao
import kotlinx.coroutines.flow.Flow

@Dao
interface FormacaoDao {
    @Query("SELECT * FROM formacoes")
    fun listar(): Flow<List<Formacao>>

    @Insert
    suspend fun inserir(formacao: Formacao)

    @Update
    suspend fun atualizar(formacao: Formacao)

    @Delete
    suspend fun excluir(formacao: Formacao)

    @Query("DELETE FROM formacoes WHERE candidatoId = :candidatoId AND dataCadastro = :dataCadastro")
    suspend fun excluir(candidatoId: Int, dataCadastro: String)

    @Query("DELETE FROM formacoes")
    suspend fun excluirTodos()
}