package br.dev.codelabs.lidacarreiras.data.repository

import br.dev.codelabs.lidacarreiras.data.dao.CertificadoDao
import br.dev.codelabs.lidacarreiras.data.model.Certificado
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CertificadoRepositorySQLite @Inject constructor(val certificadoDao: CertificadoDao) : CertificadoRepository {
    override  val certificados: Flow<List<Certificado>>
        get() = certificadoDao.listar()

    override suspend fun salvar(certificado: Certificado) {
        if (certificado.id == "") {
            certificadoDao.inserir(certificado)
        } else {
            certificadoDao.atualizar(certificado)
        }
    }

    override suspend fun excluir(certificado: Certificado) {
        certificadoDao.excluir(certificado)
    }
}