package br.dev.codelabs.lidacarreiras.data.repository

import br.dev.codelabs.lidacarreiras.data.model.Seguir
import kotlinx.coroutines.flow.Flow

interface SeguirRepository {
    val seguindo: Flow<List<Seguir>>
    suspend fun salvar(seguir: Seguir)
}