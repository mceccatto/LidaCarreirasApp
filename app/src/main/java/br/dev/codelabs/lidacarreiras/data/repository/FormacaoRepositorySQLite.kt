package br.dev.codelabs.lidacarreiras.data.repository

import br.dev.codelabs.lidacarreiras.data.dao.FormacaoDao
import br.dev.codelabs.lidacarreiras.data.model.Formacao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FormacaoRepositorySQLite @Inject constructor(val formacaoDao: FormacaoDao) : FormacaoRepository {
    override  val formacoes: Flow<List<Formacao>>
        get() = formacaoDao.listar()

    override suspend fun salvar(formacao: Formacao) {
        if (formacao.id == "") {
            formacaoDao.inserir(formacao)
        } else {
            formacaoDao.atualizar(formacao)
        }
    }

    override suspend fun excluir(formacao: Formacao) {
        formacaoDao.excluir(formacao)
    }
}