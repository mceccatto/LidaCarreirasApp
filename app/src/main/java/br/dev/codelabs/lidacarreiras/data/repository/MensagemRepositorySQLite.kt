package br.dev.codelabs.lidacarreiras.data.repository

import br.dev.codelabs.lidacarreiras.data.dao.MensagemDao
import br.dev.codelabs.lidacarreiras.data.model.Mensagem
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MensagemRepositorySQLite @Inject constructor(val mensagemDao: MensagemDao) : MensagemRepository {
    override  val mensagens: Flow<List<Mensagem>>
        get() = mensagemDao.listar()

    override suspend fun salvar(mensagem: Mensagem) {
        if (mensagem.id == "") {
            mensagemDao.inserir(mensagem)
        } else {
            mensagemDao.atualizar(mensagem)
        }
    }

    override suspend fun excluir(mensagem: Mensagem) {
        mensagemDao.excluir(mensagem)
    }
}