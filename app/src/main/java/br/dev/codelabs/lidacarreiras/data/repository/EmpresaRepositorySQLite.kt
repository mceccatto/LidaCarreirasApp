package br.dev.codelabs.lidacarreiras.data.repository

import br.dev.codelabs.lidacarreiras.data.dao.EmpresaDao
import br.dev.codelabs.lidacarreiras.data.model.Empresa
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class EmpresaRepositorySQLite @Inject constructor(val empresaDao: EmpresaDao) : EmpresaRepository {
    override  val empresas: Flow<List<Empresa>>
        get() = empresaDao.listar()

    override suspend fun salvar(empresa: Empresa) {
        if (empresa.id == "") {
            empresaDao.inserir(empresa)
        } else {
            empresaDao.atualizar(empresa)
        }
    }

    override suspend fun excluir(empresa: Empresa) {
        empresaDao.excluir(empresa)
    }
}