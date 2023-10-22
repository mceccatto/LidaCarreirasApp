package br.dev.codelabs.lidacarreiras.data.repository

import br.dev.codelabs.lidacarreiras.data.dao.VagaDao
import br.dev.codelabs.lidacarreiras.data.model.Vaga
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class VagaRepositorySQLite @Inject constructor(val vagaDao: VagaDao) : VagaRepository {
    override  val vagas: Flow<List<Vaga>>
        get() = vagaDao.listar()

    override suspend fun salvar(vaga: Vaga) {
        if (vaga.id == ""){
            vagaDao.inserir(vaga)
        } else {
            vagaDao.atualizar(vaga)
        }
    }

    override suspend fun excluir(vaga: Vaga) {
        vagaDao.excluir(vaga)
    }
}