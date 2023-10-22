package br.dev.codelabs.lidacarreiras.data.repository

import br.dev.codelabs.lidacarreiras.data.dao.SeguirDao
import br.dev.codelabs.lidacarreiras.data.model.Seguir
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SeguirRepositorySQLite @Inject constructor(val seguirDao: SeguirDao) : SeguirRepository {
    override  val seguindo: Flow<List<Seguir>>
        get() = seguirDao.listar()

    override suspend fun salvar(seguir: Seguir) {
        if (seguir.id == "" && seguir.empresaId != "") {
            seguirDao.inserir(seguir)
        } else {
            seguirDao.excluir(seguir)
        }
    }
}