package br.dev.codelabs.lidacarreiras.ui.experiencia

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.dev.codelabs.lidacarreiras.data.model.Experiencia
import br.dev.codelabs.lidacarreiras.data.repository.ExperienciaRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExperienciaViewModel @Inject constructor(val repository: ExperienciaRepository) : ViewModel() {
    var experiencia: Experiencia = Experiencia()

    private var _experiencias = MutableStateFlow(listOf<Experiencia>())

    val experiencias : Flow<List<Experiencia>> = _experiencias

    init {
        viewModelScope.launch {
            repository.experiencias.collect{ experiencias ->
                _experiencias.value = experiencias
            }
        }
    }

    fun novo() {
        this.experiencia = Experiencia()
    }

    fun editar(experiencia: Experiencia) {
        this.experiencia = experiencia
    }

    fun salvar() = viewModelScope.launch {
        repository.salvar(experiencia)
    }

    fun excluir(experiencia: Experiencia) = viewModelScope.launch {
        repository.excluir(experiencia)
    }
}