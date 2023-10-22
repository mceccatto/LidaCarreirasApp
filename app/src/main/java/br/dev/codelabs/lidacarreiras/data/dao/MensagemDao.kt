package br.dev.codelabs.lidacarreiras.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import br.dev.codelabs.lidacarreiras.data.model.Mensagem
import kotlinx.coroutines.flow.Flow

@Dao
interface MensagemDao {
    @Query("SELECT * FROM mensagens")
    fun listar(): Flow<List<Mensagem>>

    @Insert
    suspend fun inserir(mensagem: Mensagem)

    @Update
    suspend fun atualizar(mensagem: Mensagem)

    @Delete
    suspend fun excluir(mensagem: Mensagem)

    @Query("DELETE FROM mensagens WHERE empresaId = :empresaId OR candidatoId = :candidatoId AND dataCadastro = :dataCadastro")
    suspend fun excluir(empresaId: Int, candidatoId: Int, dataCadastro: String)

    @Query("DELETE FROM mensagens")
    suspend fun excluirTodos()
}