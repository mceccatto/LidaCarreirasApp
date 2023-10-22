package br.dev.codelabs.lidacarreiras.data.repository

import br.dev.codelabs.lidacarreiras.data.model.Formacao
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Named

class FormacaoRepositoryFirebase @Inject constructor(@Named("formacoes") private val formacoesRef: CollectionReference) : FormacaoRepository {
    private var _formacoes = MutableStateFlow(listOf<Formacao>())
    override val formacoes: Flow<List<Formacao>>
        get() = _formacoes.asStateFlow()

    init {
        formacoesRef.addSnapshotListener { snapshot, _ ->
            if (snapshot != null) {
                var formacoes = mutableListOf<Formacao>()
                snapshot.documents.forEach { doc ->
                    val formacao = doc.toObject<Formacao>()
                    if (formacao != null) {
                        formacao.id = doc.id
                        formacao.docId = doc.id
                        formacoes.add(formacao)
                    }
                }
                _formacoes.value = formacoes
            } else {
                _formacoes = MutableStateFlow(listOf())
            }
        }
    }

    override suspend fun salvar(formacao: Formacao) {
        if (formacao.docId.isNullOrEmpty()) {
            var doc = formacoesRef.document()
            formacao.id = doc.id
            formacao.docId = doc.id
            doc.set(formacao)
        } else {
            formacoesRef.document(formacao.docId).set(formacao)
        }
    }

    override suspend fun excluir(formacao: Formacao) {
        formacoesRef.document(formacao.docId).delete()
    }
}