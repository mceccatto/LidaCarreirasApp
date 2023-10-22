package br.dev.codelabs.lidacarreiras.data.repository

import br.dev.codelabs.lidacarreiras.data.model.Empresa
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Named

class EmpresaRepositoryFirebase @Inject constructor(@Named("empresas") private val empresasRef: CollectionReference) : EmpresaRepository {
    private var _empresas = MutableStateFlow(listOf<Empresa>())
    override val empresas: Flow<List<Empresa>>
        get() = _empresas.asStateFlow()

    init {
        empresasRef.addSnapshotListener { snapshot, _ ->
            if (snapshot != null) {
                var empresas = mutableListOf<Empresa>()
                snapshot.documents.forEach { doc ->
                    val empresa = doc.toObject<Empresa>()
                    if (empresa != null){
                        empresa.id = doc.id
                        empresa.docId = doc.id
                        empresas.add(empresa)
                    }
                }
                _empresas.value = empresas
            } else {
                _empresas = MutableStateFlow(listOf())
            }
        }
    }

    override suspend fun salvar(empresa: Empresa) {
        if (empresa.docId.isNullOrEmpty()) {
            var doc = empresasRef.document()
            empresa.id = doc.id
            empresa.docId = doc.id
            doc.set(empresa)
        } else {
            empresasRef.document(empresa.docId).set(empresa)
        }
    }

    override suspend fun excluir(empresa: Empresa) {
        empresasRef.document(empresa.docId).delete()
    }
}