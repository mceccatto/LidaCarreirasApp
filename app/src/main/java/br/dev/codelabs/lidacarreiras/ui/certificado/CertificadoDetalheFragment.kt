package br.dev.codelabs.lidacarreiras.ui.certificado

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.activityViewModels
import br.dev.codelabs.lidacarreiras.databinding.FragmentCertificadoDetalheBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CertificadoDetalheFragment : Fragment() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val viewModel : CertificadoViewModel by activityViewModels()

        val binding = FragmentCertificadoDetalheBinding.inflate(layoutInflater)

        var certificado = viewModel.certificado

        binding.inputInstituicao.setText(certificado.instituicao)
        binding.inputTitulo.setText(certificado.titulo)
        //binding.inputImagem.setText(certificado.arquivo)
        return binding.root
    }
}