package br.dev.codelabs.lidacarreiras.data.repository

import br.dev.codelabs.lidacarreiras.data.model.Mensagem
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Named

class MensagemRepositoryFirebase @Inject constructor(@Named("mensagens") private val mensagensRef: CollectionReference) : MensagemRepository {
    private var _mensagens = MutableStateFlow(listOf<Mensagem>())
    override val mensagens: Flow<List<Mensagem>>
        get() = _mensagens.asStateFlow()

    init {
        mensagensRef.addSnapshotListener { snapshot, _ ->
            if (snapshot != null) {
                var mensagens = mutableListOf<Mensagem>()
                snapshot.documents.forEach { doc ->
                    val mensagem = doc.toObject<Mensagem>()
                    if (mensagem != null) {
                        mensagem.id = doc.id
                        mensagem.docId = doc.id
                        mensagens.add(mensagem)
                    }
                }
                _mensagens.value = mensagens
            } else {
                _mensagens = MutableStateFlow(listOf())
            }
        }
    }

    override suspend fun salvar(mensagem: Mensagem) {
        if (mensagem.docId.isNullOrEmpty()) {
            var doc = mensagensRef.document()
            mensagem.id = doc.id
            mensagem.docId = doc.id
            doc.set(mensagem)
        } else {
            mensagensRef.document(mensagem.docId).set(mensagem)
        }
    }

    override suspend fun excluir(mensagem: Mensagem) {
        mensagensRef.document(mensagem.docId).delete()
    }
}