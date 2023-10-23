package br.dev.codelabs.lidacarreiras.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import br.dev.codelabs.lidacarreiras.data.model.Certificado
import kotlinx.coroutines.flow.Flow

@Dao
interface CertificadoDao {
    @Query("SELECT * FROM certificados")
    fun listar(): Flow<List<Certificado>>

    @Insert
    suspend fun inserir(certificado: Certificado)

    @Update
    suspend fun atualizar(certificado: Certificado)

    @Delete
    suspend fun excluir(certificado: Certificado)

    @Query("DELETE FROM certificados WHERE id = :id")
    suspend fun excluir(id: Int)

    @Query("DELETE FROM certificados")
    suspend fun excluirTodos()
}