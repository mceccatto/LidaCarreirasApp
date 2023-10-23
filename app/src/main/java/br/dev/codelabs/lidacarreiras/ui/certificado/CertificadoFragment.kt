package br.dev.codelabs.lidacarreiras.ui.certificado

import android.R.attr.bitmap
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import br.dev.codelabs.lidacarreiras.databinding.FragmentCertificadoBinding
import dagger.hilt.android.AndroidEntryPoint
import java.io.ByteArrayOutputStream
import java.time.LocalDateTime


@AndroidEntryPoint
class CertificadoFragment : Fragment() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val viewModel : CertificadoViewModel by activityViewModels()

        val binding = FragmentCertificadoBinding.inflate(layoutInflater)

        viewModel.novo()

        val candidatoId = "3cG9Og9eBu0ndyYfHJH7"

        var base64: String = ""

        val galleryLauncher = registerForActivityResult(ActivityResultContracts.GetContent()) {
            val galleryUri = it
            try {
                binding.inputImagem.setImageURI(galleryUri)



                binding.btnSalvar.visibility = View.VISIBLE
            } catch (e:Exception) {
                e.printStackTrace()
            }
        }

        binding.btnAnexar.setOnClickListener {
            galleryLauncher.launch("image/*")
        }
        binding.btnSalvar.setOnClickListener {
            try {
                viewModel.certificado.candidatoId = candidatoId
                viewModel.certificado.instituicao = binding.inputInstituicao.text.toString()
                viewModel.certificado.titulo = binding.inputTitulo.text.toString()
                //viewModel.certificado.arquivo = binding.inputImagem.text.toString()
                viewModel.certificado.dataCadastro = LocalDateTime.now().toString()
            } catch (e: Exception){}
            viewModel.salvar()
            findNavController().popBackStack()
        }
        return binding.root
    }
}