package br.dev.codelabs.lidacarreiras.data.repository

import br.dev.codelabs.lidacarreiras.data.model.Mensagem
import kotlinx.coroutines.flow.Flow

interface MensagemRepository {
    val mensagens: Flow<List<Mensagem>>
    suspend fun salvar(mensagem: Mensagem)
    suspend fun excluir(mensagem: Mensagem)
}