package br.dev.codelabs.lidacarreiras.data.repository

import br.dev.codelabs.lidacarreiras.data.model.Certificado
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Named

class CertificadoRepositoryFirebase @Inject constructor(@Named("certificados") private val certificadosRef: CollectionReference) : CertificadoRepository {
    private var _certificados = MutableStateFlow(listOf<Certificado>())
    override val certificados: Flow<List<Certificado>>
        get() = _certificados.asStateFlow()

    init {
        certificadosRef.addSnapshotListener { snapshot, _ ->
            if (snapshot != null) {
                var certificados = mutableListOf<Certificado>()
                snapshot.documents.forEach { doc ->
                    val certificado = doc.toObject<Certificado>()
                    if (certificado != null) {
                        certificado.id = doc.id
                        certificado.docId = doc.id
                        certificados.add(certificado)
                    }
                }
                _certificados.value = certificados
            } else {
                _certificados = MutableStateFlow(listOf())
            }
        }
    }

    override suspend fun salvar(certificado: Certificado) {
        if (certificado.docId.isNullOrEmpty()) {
            var doc = certificadosRef.document()
            certificado.id = doc.id
            certificado.docId = doc.id
            doc.set(certificado)
        } else {
            certificadosRef.document(certificado.docId).set(certificado)
        }
    }

    override suspend fun excluir(certificado: Certificado) {
        certificadosRef.document(certificado.docId).delete()
    }
}