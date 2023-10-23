package br.dev.codelabs.lidacarreiras.ui.curtida

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.dev.codelabs.lidacarreiras.data.model.Curtida
import br.dev.codelabs.lidacarreiras.data.repository.CurtidaRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurtidaViewModel @Inject constructor(val repository: CurtidaRepository) : ViewModel() {
    var curtida: Curtida = Curtida()
    var vagaId: String = ""

    private var _curtidas = MutableStateFlow(listOf<Curtida>())

    val curtidas : Flow<List<Curtida>> = _curtidas

    init {
        viewModelScope.launch {
            repository.curtidas.collect{ curtidas ->
                _curtidas.value = curtidas
            }
        }
    }

    fun curtir() = viewModelScope.launch {
        repository.curtir(curtida)
    }

    fun descurtir() = viewModelScope.launch {
        repository.descurtir(curtida)
    }

    fun salvar() = viewModelScope.launch {
        repository.salvar(curtida)
    }
}