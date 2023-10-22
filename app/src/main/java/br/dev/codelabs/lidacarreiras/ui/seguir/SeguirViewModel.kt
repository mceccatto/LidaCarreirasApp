package br.dev.codelabs.lidacarreiras.ui.seguir

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.dev.codelabs.lidacarreiras.data.model.Seguir
import br.dev.codelabs.lidacarreiras.data.repository.SeguirRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SeguirViewModel @Inject constructor(val repository: SeguirRepository) : ViewModel() {
    var seguir: Seguir = Seguir()

    private var _seguindo = MutableStateFlow(listOf<Seguir>())

    val seguindo : Flow<List<Seguir>> = _seguindo

    init {
        viewModelScope.launch {
            repository.seguindo.collect{ seguindo ->
                _seguindo.value = seguindo
            }
        }
    }

    fun salvar() = viewModelScope.launch {
        repository.salvar(seguir)
    }
}