package br.dev.codelabs.lidacarreiras.data.repository

import br.dev.codelabs.lidacarreiras.data.model.Empresa
import kotlinx.coroutines.flow.Flow

interface EmpresaRepository {
    val empresas: Flow<List<Empresa>>
    suspend fun salvar(empresa: Empresa)
    suspend fun excluir(empresa: Empresa)
}