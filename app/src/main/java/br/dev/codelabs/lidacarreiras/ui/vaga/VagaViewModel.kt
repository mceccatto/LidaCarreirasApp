package br.dev.codelabs.lidacarreiras.ui.vaga

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.dev.codelabs.lidacarreiras.data.model.Vaga
import br.dev.codelabs.lidacarreiras.data.repository.VagaRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VagaViewModel @Inject constructor(val repository: VagaRepository) : ViewModel() {
    var vaga: Vaga = Vaga()

    private var _vagas = MutableStateFlow(listOf<Vaga>())

    val vagas : Flow<List<Vaga>> = _vagas

    init {
        viewModelScope.launch {
            repository.vagas.collect{ vagas ->
                _vagas.value = vagas
            }
        }
    }

    fun novo() {
        this.vaga = Vaga()
    }

    fun editar(vaga: Vaga) {
        this.vaga = vaga
    }

    fun salvar() = viewModelScope.launch {
        repository.salvar(vaga)
    }

    fun excluir(vaga: Vaga) = viewModelScope.launch {
        repository.excluir(vaga)
    }
}