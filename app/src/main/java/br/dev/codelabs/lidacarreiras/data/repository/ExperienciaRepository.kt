package br.dev.codelabs.lidacarreiras.data.repository

import br.dev.codelabs.lidacarreiras.data.model.Experiencia
import kotlinx.coroutines.flow.Flow

interface ExperienciaRepository {
    val experiencias: Flow<List<Experiencia>>
    suspend fun salvar(experiencia: Experiencia)
    suspend fun excluir(experiencia: Experiencia)
}