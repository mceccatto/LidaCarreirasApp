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
import br.dev.codelabs.lidacarreiras.databinding.FragmentExperienciaBinding
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDateTime

@AndroidEntryPoint
class ExperienciaFragment : Fragment() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val viewModel : ExperienciaViewModel by activityViewModels()

        val binding = FragmentExperienciaBinding.inflate(layoutInflater)

        viewModel.novo()

        binding.btnSalvar.setOnClickListener {
            try {
                viewModel.experiencia.empresa = binding.inputEmpresa.text.toString()
                viewModel.experiencia.cargo = binding.inputCargo.text.toString()
                viewModel.experiencia.atribuicoes = binding.inputAtribuicoes.text.toString()
                viewModel.experiencia.dataContratacao = binding.inputDataContratacao.text.toString()
                viewModel.experiencia.dataDesligamento = binding.inputDataDesligamento.text.toString()
                viewModel.experiencia.dataCadastro = LocalDateTime.now().toString()
            } catch (e: Exception){}
            viewModel.salvar()
            findNavController().popBackStack()
        }
        return binding.root
    }
}