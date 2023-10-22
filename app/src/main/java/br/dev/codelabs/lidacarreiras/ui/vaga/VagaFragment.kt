package br.dev.codelabs.lidacarreiras.ui.vaga

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import br.dev.codelabs.lidacarreiras.databinding.FragmentVagaBinding
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDateTime

@AndroidEntryPoint
class VagaFragment : Fragment() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val viewModel : VagaViewModel by activityViewModels()

        val binding = FragmentVagaBinding.inflate(layoutInflater)

        var vaga = viewModel.vaga

        binding.inputTitulo.setText(vaga.titulo)
        binding.inputDescricao.setText(vaga.descricao)
        binding.inputImagem.setText(vaga.imagem)
        binding.btnSalvar.setOnClickListener {
            try {
                viewModel.vaga.empresaId = "87gyhA87SGa87gsA8G"
                viewModel.vaga.titulo = binding.inputTitulo.text.toString()
                viewModel.vaga.descricao = binding.inputDescricao.text.toString()
                viewModel.vaga.imagem = binding.inputImagem.text.toString()
                viewModel.vaga.dataCadastro = LocalDateTime.now().toString()
            } catch (e: Exception){}
            viewModel.salvar()
            findNavController().popBackStack()
        }
        return binding.root
    }
}