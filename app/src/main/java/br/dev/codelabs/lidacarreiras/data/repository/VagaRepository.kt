package br.dev.codelabs.lidacarreiras.data.repository

import br.dev.codelabs.lidacarreiras.data.model.Vaga
import kotlinx.coroutines.flow.Flow

interface VagaRepository {
    val vagas: Flow<List<Vaga>>
    suspend fun salvar(vaga: Vaga)
    suspend fun excluir(vaga: Vaga)
}