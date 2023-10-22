package br.dev.codelabs.lidacarreiras.data.repository

import br.dev.codelabs.lidacarreiras.data.model.Candidato
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Named

class CandidatoRepositoryFirebase @Inject constructor(@Named("candidatos") private val candidatosRef: CollectionReference) : CandidatoRepository {
    private var _candidatos = MutableStateFlow(listOf<Candidato>())

    override val candidatos: Flow<List<Candidato>>
        get() = _candidatos.asStateFlow()

    init {
        candidatosRef.addSnapshotListener { snapshot, _ ->
            if (snapshot != null) {
                var candidatos = mutableListOf<Candidato>()
                snapshot.documents.forEach { doc ->
                    val candidato = doc.toObject<Candidato>()
                    if (candidato != null) {
                        candidato.id = doc.id
                        candidato.docId = doc.id
                        candidatos.add(candidato)
                    }
                }
                _candidatos.value = candidatos
            } else {
                _candidatos = MutableStateFlow(listOf())
            }
        }
    }

    override suspend fun salvar(candidato: Candidato) {
        if (candidato.docId.isNullOrEmpty()) {
            var doc = candidatosRef.document()
            candidato.id = doc.id
            candidato.docId = doc.id
            doc.set(candidato)
        } else {
            candidatosRef.document(candidato.docId).set(candidato)
        }
    }

    override suspend fun excluir(candidato: Candidato) {
        candidatosRef.document(candidato.docId).delete()
    }
}