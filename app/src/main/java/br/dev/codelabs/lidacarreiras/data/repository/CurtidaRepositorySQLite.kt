package br.dev.codelabs.lidacarreiras.data.repository

import br.dev.codelabs.lidacarreiras.data.dao.CurtidaDao
import br.dev.codelabs.lidacarreiras.data.model.Curtida
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CurtidaRepositorySQLite @Inject constructor(val candidatoDao: CurtidaDao) : CurtidaRepository {
    override val curtidas: Flow<List<Curtida>>
        get() = candidatoDao.listar()

    override suspend fun curtir(curtida: Curtida) {
        candidatoDao.curtir(curtida)
    }

    override suspend fun descurtir(curtida: Curtida) {
        candidatoDao.descurtir(curtida)
    }

    override suspend fun salvar(curtida: Curtida) {
        if (curtida.id == "" && curtida.candidatoId != "") {
            candidatoDao.inserir(curtida)
        } else {
            candidatoDao.excluir(curtida)
        }
    }
}