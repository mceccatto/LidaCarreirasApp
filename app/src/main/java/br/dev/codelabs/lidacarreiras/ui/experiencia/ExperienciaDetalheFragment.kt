package br.dev.codelabs.lidacarreiras.ui.experiencia

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import br.dev.codelabs.lidacarreiras.databinding.FragmentExperienciaDetalheBinding
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDateTime

@AndroidEntryPoint
class ExperienciaDetalheFragment : Fragment() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val viewModel : ExperienciaViewModel by activityViewModels()

        val binding = FragmentExperienciaDetalheBinding.inflate(layoutInflater)

        var experiencia = viewModel.experiencia

        binding.inputEmpresa.setText(experiencia.empresa)
        binding.inputCargo.setText(experiencia.cargo)
        binding.inputAtribuicoes.setText(experiencia.atribuicoes)
        binding.inputDataContratacao.setText(experiencia.dataContratacao)
        binding.inputDataDesligamento.setText(experiencia.dataDesligamento)
        return binding.root
    }
}