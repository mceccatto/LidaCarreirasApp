package br.dev.codelabs.lidacarreiras.data.repository

import br.dev.codelabs.lidacarreiras.data.dao.ExperienciaDao
import br.dev.codelabs.lidacarreiras.data.model.Experiencia
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ExperienciaRepositorySQLite @Inject constructor(val experienciaDao: ExperienciaDao) : ExperienciaRepository {
    override  val experiencias: Flow<List<Experiencia>>
        get() = experienciaDao.listar()

    override suspend fun salvar(experiencia: Experiencia) {
        if (experiencia.id == "") {
            experienciaDao.inserir(experiencia)
        } else {
            experienciaDao.atualizar(experiencia)
        }
    }

    override suspend fun excluir(empresa: Experiencia) {
        experienciaDao.excluir(empresa)
    }
}