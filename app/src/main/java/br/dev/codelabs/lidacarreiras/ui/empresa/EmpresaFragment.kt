package br.dev.codelabs.lidacarreiras.ui.empresa

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import br.dev.codelabs.lidacarreiras.databinding.FragmentEmpresaBinding
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDateTime

@AndroidEntryPoint
class EmpresaFragment : Fragment() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val viewModel : EmpresaViewModel by activityViewModels()

        val binding = FragmentEmpresaBinding.inflate(layoutInflater)

        var empresa = viewModel.empresa

        binding.inputCnpj.setText(empresa.cnpj)
        binding.inputRasaoSocial.setText(empresa.rasaoSocial)
        binding.inputNomeFantasia.setText(empresa.nomeFantasia)
        binding.inputTelefone.setText(empresa.telefone)
        binding.inputEmail.setText(empresa.email)
        binding.inputSenha.setText(empresa.senha)
        binding.inputImagem.setText(empresa.imagem)
        binding.btnSalvar.setOnClickListener {
            try {
                viewModel.empresa.cnpj = binding.inputCnpj.text.toString()
                viewModel.empresa.rasaoSocial = binding.inputRasaoSocial.text.toString()
                viewModel.empresa.nomeFantasia = binding.inputNomeFantasia.text.toString()
                viewModel.empresa.telefone = binding.inputTelefone.text.toString()
                viewModel.empresa.email = binding.inputEmail.text.toString()
                viewModel.empresa.senha = binding.inputSenha.text.toString()
                viewModel.empresa.imagem = binding.inputImagem.text.toString()
                viewModel.empresa.dataCadastro = LocalDateTime.now().toString()
            } catch (e: Exception){}
            viewModel.salvar()
            findNavController().popBackStack()
        }
        return binding.root
    }
}