package br.dev.codelabs.lidacarreiras.ui.empresa

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.dev.codelabs.lidacarreiras.data.model.Empresa
import br.dev.codelabs.lidacarreiras.data.repository.EmpresaRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EmpresaViewModel @Inject constructor(val repository: EmpresaRepository) : ViewModel() {
    var empresa: Empresa = Empresa()

    private var _empresas = MutableStateFlow(listOf<Empresa>())

    val empresas : Flow<List<Empresa>> = _empresas

    init {
        viewModelScope.launch {
            repository.empresas.collect{ empresas ->
                _empresas.value = empresas
            }
        }
    }

    fun novo() {
        this.empresa = Empresa()
    }

    fun editar(empresa: Empresa) {
        this.empresa = empresa
    }

    fun salvar() = viewModelScope.launch {
        repository.salvar(empresa)
    }

    fun excluir(empresa: Empresa) = viewModelScope.launch {
        repository.excluir(empresa)
    }
}