package br.dev.codelabs.lidacarreiras.data.repository

import br.dev.codelabs.lidacarreiras.data.dao.CandidatoDao
import br.dev.codelabs.lidacarreiras.data.model.Candidato
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CandidatoRepositorySQLite @Inject constructor(val candidatoDao: CandidatoDao) : CandidatoRepository {
    override  val candidatos: Flow<List<Candidato>>
        get() = candidatoDao.listar()

    override suspend fun salvar(candidato: Candidato) {
        if (candidato.id == "") {
            candidatoDao.inserir(candidato)
        } else {
            candidatoDao.atualizar(candidato)
        }
    }

    override suspend fun excluir(candidato: Candidato) {
        candidatoDao.excluir(candidato)
    }
}