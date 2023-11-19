package br.dev.codelabs.lidacarreiras.data.repository

import br.dev.codelabs.lidacarreiras.data.model.Curtida
import br.dev.codelabs.lidacarreiras.databinding.FragmentVagaDetalheBinding
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Named

class CurtidaRepositoryFirebase @Inject constructor(@Named("curtidas") private val curtidasRef: CollectionReference) : CurtidaRepository {
    private var _curtidas = MutableStateFlow(listOf<Curtida>())
    override val curtidas: Flow<List<Curtida>>
        get() = _curtidas.asStateFlow()

    init {
        curtidasRef.addSnapshotListener { snapshot, _ ->
            if (snapshot != null) {
                var curtidas = mutableListOf<Curtida>()
                snapshot.documents.forEach { doc ->
                    val curtida = doc.toObject<Curtida>()
                    if (curtida != null) {
                        curtida.id = doc.id
                        curtida.docId = doc.id
                        curtidas.add(curtida)
                    }
                }
                _curtidas.value = curtidas
            } else {
                _curtidas = MutableStateFlow(listOf())
            }
        }
    }

    override suspend fun buscaId(vagaId: String, candidatoId: String): Array<Curtida> {
        return arrayOf<Curtida>()
    }

    override suspend fun curtir(curtida: Curtida) {
        if(curtida.id.isNullOrEmpty()) {
            var doc = curtidasRef.document()
            curtida.id = doc.id
            curtida.docId = doc.id
            doc.set(curtida)
        }
    }

    override suspend fun descurtir(curtida: Curtida) {
        curtidasRef.document(curtida.docId).delete()
    }

    override suspend fun salvar(curtida: Curtida) {
        if (curtida.id.isNullOrEmpty() && curtida.candidatoId.isNotBlank()) {
            var doc = curtidasRef.document()
            curtida.id = doc.id
            curtida.docId = doc.id
            doc.set(curtida)
        } else {
            curtidasRef.document(curtida.docId).delete()
        }
    }
}