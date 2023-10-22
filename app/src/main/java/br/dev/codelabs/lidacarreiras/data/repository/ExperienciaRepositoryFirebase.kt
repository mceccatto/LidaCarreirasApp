package br.dev.codelabs.lidacarreiras.data.repository

import br.dev.codelabs.lidacarreiras.data.model.Experiencia
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Named

class ExperienciaRepositoryFirebase @Inject constructor(@Named("experiencias") private val experienciasRef: CollectionReference) : ExperienciaRepository {
    private var _experiencias = MutableStateFlow(listOf<Experiencia>())
    override val experiencias: Flow<List<Experiencia>>
        get() = _experiencias.asStateFlow()

    init {
        experienciasRef.addSnapshotListener { snapshot, _ ->
            if (snapshot != null) {
                var experiencias = mutableListOf<Experiencia>()
                snapshot.documents.forEach { doc ->
                    val experiencia = doc.toObject<Experiencia>()
                    if (experiencia != null) {
                        experiencia.id = doc.id
                        experiencia.docId = doc.id
                        experiencias.add(experiencia)
                    }
                }
                _experiencias.value = experiencias
            } else {
                _experiencias = MutableStateFlow(listOf())
            }
        }
    }

    override suspend fun salvar(experiencia: Experiencia) {
        if (experiencia.docId.isNullOrEmpty()) {
            var doc = experienciasRef.document()
            experiencia.id = doc.id
            experiencia.docId = doc.id
            doc.set(experiencia)
        } else {
            experienciasRef.document(experiencia.docId).set(experiencia)
        }
    }

    override suspend fun excluir(experiencia: Experiencia) {
        experienciasRef.document(experiencia.docId).delete()
    }
}