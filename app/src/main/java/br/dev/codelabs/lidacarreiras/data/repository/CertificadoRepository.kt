package br.dev.codelabs.lidacarreiras.data.repository

import br.dev.codelabs.lidacarreiras.data.model.Certificado
import kotlinx.coroutines.flow.Flow

interface CertificadoRepository {
    val certificados: Flow<List<Certificado>>
    suspend fun salvar(certificado: Certificado)
    suspend fun excluir(certificado: Certificado)
}