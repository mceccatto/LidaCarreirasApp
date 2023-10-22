package br.dev.codelabs.lidacarreiras.data.repository

import br.dev.codelabs.lidacarreiras.data.model.Seguir
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Named

class SeguirRepositoryFirebase @Inject constructor(@Named("seguindo") private val seguindoRef: CollectionReference) : SeguirRepository {
    private var _seguindo = MutableStateFlow(listOf<Seguir>())
    override val seguindo: Flow<List<Seguir>>
        get() = _seguindo.asStateFlow()

    init {
        seguindoRef.addSnapshotListener { snapshot, _ ->
            if (snapshot != null) {
                var seguindo = mutableListOf<Seguir>()
                snapshot.documents.forEach { doc ->
                    val seguir = doc.toObject<Seguir>()
                    if (seguir != null) {
                        seguir.id = doc.id
                        seguir.docId = doc.id
                        seguindo.add(seguir)
                    }
                }
                _seguindo.value = seguindo
            } else {
                _seguindo = MutableStateFlow(listOf())
            }
        }
    }

    override suspend fun salvar(seguir: Seguir) {
        if (seguir.id.isNullOrEmpty() && seguir.candidatoId.isNotEmpty()) {
            var doc = seguindoRef.document()
            seguir.id = doc.id
            seguir.docId = doc.id
            doc.set(seguir)
        } else {
            seguindoRef.document(seguir.docId).delete()
        }
    }
}