package br.dev.codelabs.lidacarreiras.ui.certificado

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.util.Base64
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
        binding.inputImagem.setImageBitmap(decodePicString(certificado.arquivo))
        return binding.root
    }

    fun decodePicString (encodedString: String): Bitmap {
        val imageBytes = Base64.decode(encodedString, Base64.DEFAULT)
        val decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
        return decodedImage
    }
}