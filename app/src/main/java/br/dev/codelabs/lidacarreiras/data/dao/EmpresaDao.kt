package br.dev.codelabs.lidacarreiras.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import br.dev.codelabs.lidacarreiras.data.model.Empresa
import kotlinx.coroutines.flow.Flow

@Dao
interface EmpresaDao {
    @Query("SELECT * FROM empresas")
    fun listar(): Flow<List<Empresa>>

    @Insert
    suspend fun inserir(empresa: Empresa)

    @Update
    suspend fun atualizar(empresa: Empresa)

    @Delete
    suspend fun excluir(empresa: Empresa)

    @Query("DELETE FROM empresas WHERE id = :id")
    suspend fun excluir(id: Int)

    @Query("DELETE FROM empresas")
    suspend fun excluirTodos()
}