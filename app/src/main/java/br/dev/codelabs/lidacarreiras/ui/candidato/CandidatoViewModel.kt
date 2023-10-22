package br.dev.codelabs.lidacarreiras.ui.candidato

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.dev.codelabs.lidacarreiras.data.model.Candidato
import br.dev.codelabs.lidacarreiras.data.repository.CandidatoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CandidatoViewModel @Inject constructor(val repository: CandidatoRepository) : ViewModel() {
    var candidato: Candidato = Candidato()

    private var _candidatos = MutableStateFlow(listOf<Candidato>())

    val candidatos : Flow<List<Candidato>> = _candidatos

    init {
        viewModelScope.launch {
            repository.candidatos.collect{ candidatos ->
                _candidatos.value = candidatos
            }
        }
    }

    fun novo() {
        this.candidato = Candidato()
    }

    fun editar(candidato: Candidato) {
        this.candidato = candidato
    }

    fun salvar() = viewModelScope.launch {
        repository.salvar(candidato)
    }

    fun excluir(candidato: Candidato) = viewModelScope.launch {
        repository.excluir(candidato)
    }
}