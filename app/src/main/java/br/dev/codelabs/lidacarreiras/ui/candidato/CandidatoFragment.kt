package br.dev.codelabs.lidacarreiras.ui.candidato

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import br.dev.codelabs.lidacarreiras.databinding.FragmentCandidatoBinding
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDateTime

@AndroidEntryPoint
class CandidatoFragment : Fragment() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val viewModel : CandidatoViewModel by activityViewModels()

        val binding = FragmentCandidatoBinding.inflate(layoutInflater)

        viewModel.novo()

        binding.inputImagem.setText("semfoto.jpg")
        binding.btnSalvar.setOnClickListener {
            try {
                viewModel.candidato.cpf = binding.inputCpf.text.toString()
                viewModel.candidato.nome = binding.inputNome.text.toString()
                viewModel.candidato.dataNascimento = binding.inputDataNascimento.text.toString()
                viewModel.candidato.email = binding.inputEmail.text.toString()
                viewModel.candidato.senha = binding.inputSenha.text.toString()
                viewModel.candidato.imagem = binding.inputImagem.text.toString()
                viewModel.candidato.dataCadastro = LocalDateTime.now().toString()
            } catch (e: Exception){}
            viewModel.salvar()
            findNavController().popBackStack()
        }
        return binding.root
    }
}