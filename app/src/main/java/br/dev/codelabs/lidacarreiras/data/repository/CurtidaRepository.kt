package br.dev.codelabs.lidacarreiras.data.repository

import br.dev.codelabs.lidacarreiras.data.model.Curtida
import kotlinx.coroutines.flow.Flow

interface CurtidaRepository {
    val curtidas: Flow<List<Curtida>>
    suspend fun curtir(curtida: Curtida)
    suspend fun descurtir(curtida: Curtida)
    suspend fun salvar(curtida: Curtida)
}