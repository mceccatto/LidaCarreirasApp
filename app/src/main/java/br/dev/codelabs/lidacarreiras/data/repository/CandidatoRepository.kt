package br.dev.codelabs.lidacarreiras.data.repository

import br.dev.codelabs.lidacarreiras.data.model.Candidato
import kotlinx.coroutines.flow.Flow

interface CandidatoRepository {
    val candidatos: Flow<List<Candidato>>
    suspend fun salvar(candidato: Candidato)
    suspend fun excluir(candidato: Candidato)
}