package br.dev.codelabs.lidacarreiras.ui.certificado

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.dev.codelabs.lidacarreiras.data.model.Certificado
import br.dev.codelabs.lidacarreiras.data.repository.CertificadoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CertificadoViewModel @Inject constructor(val repository: CertificadoRepository) : ViewModel() {
    var certificado: Certificado = Certificado()

    private var _certificados = MutableStateFlow(listOf<Certificado>())

    val certificados : Flow<List<Certificado>> = _certificados

    init {
        viewModelScope.launch {
            repository.certificados.collect{ certificados ->
                _certificados.value = certificados
            }
        }
    }

    fun novo() {
        this.certificado = Certificado()
    }

    fun editar(certificado: Certificado) {
        this.certificado = certificado
    }

    fun salvar() = viewModelScope.launch {
        repository.salvar(certificado)
    }

    fun excluir(certificado: Certificado) = viewModelScope.launch {
        repository.excluir(certificado)
    }
}