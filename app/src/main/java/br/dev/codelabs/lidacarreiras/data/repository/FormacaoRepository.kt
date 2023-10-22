package br.dev.codelabs.lidacarreiras.data.repository

import br.dev.codelabs.lidacarreiras.data.model.Formacao
import kotlinx.coroutines.flow.Flow

interface FormacaoRepository {
    val formacoes: Flow<List<Formacao>>
    suspend fun salvar(formacao: Formacao)
    suspend fun excluir(formacao: Formacao)
}