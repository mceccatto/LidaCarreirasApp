package br.dev.codelabs.lidacarreiras.data.repository

import br.dev.codelabs.lidacarreiras.data.model.Vaga
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Named

class VagaRepositoryFirebase @Inject constructor(@Named("vagas") private val vagasRef: CollectionReference) : VagaRepository {
    private var _vagas = MutableStateFlow(listOf<Vaga>())
    override val vagas: Flow<List<Vaga>>
        get() = _vagas.asStateFlow()

    init {
        vagasRef.addSnapshotListener { snapshot, _ ->
            if (snapshot != null) {
                var vagas = mutableListOf<Vaga>()
                snapshot.documents.forEach { doc ->
                    val vaga = doc.toObject<Vaga>()
                    if (vaga != null) {
                        vaga.id = doc.id
                        vaga.docId = doc.id
                        vagas.add(vaga)
                    }
                }
                _vagas.value = vagas
            } else {
                _vagas = MutableStateFlow(listOf())
            }
        }
    }

    override suspend fun salvar(vaga: Vaga) {
        if (vaga.docId.isNullOrEmpty()) {
            var doc = vagasRef.document()
            vaga.id = doc.id
            vaga.docId = doc.id
            doc.set(vaga)
        } else {
            vagasRef.document(vaga.docId).set(vaga)
        }
    }

    override suspend fun excluir(vaga: Vaga) {
        vagasRef.document(vaga.docId).delete()
    }
}