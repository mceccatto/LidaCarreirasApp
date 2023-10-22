package br.dev.codelabs.lidacarreiras.ui.vaga

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.activityViewModels
import br.dev.codelabs.lidacarreiras.R
import br.dev.codelabs.lidacarreiras.databinding.FragmentVagaDetalheBinding
import br.dev.codelabs.lidacarreiras.ui.curtida.CurtidaViewModel
import br.dev.codelabs.lidacarreiras.ui.seguir.SeguirViewModel
import coil.load
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDateTime

@AndroidEntryPoint
class VagaDetalheFragment : Fragment() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val viewModelVaga : VagaViewModel by activityViewModels()
        val viewModelCurtida : CurtidaViewModel by activityViewModels()
        val viewModelSeguir : SeguirViewModel by activityViewModels()

        val binding = FragmentVagaDetalheBinding.inflate(layoutInflater)

        var vaga = viewModelVaga.vaga
        var curtida = viewModelCurtida.curtida
        var seguir = viewModelSeguir.seguir

        val candidatoId = "3cG9Og9eBu0ndyYfHJH7"

        binding.inputTitulo.setText(vaga.titulo)
        binding.inputDescricao.setText(vaga.descricao)
        Firebase.storage.getReference(vaga.imagem).downloadUrl.addOnSuccessListener { imageUrl ->
            binding.imgFoto.load(imageUrl)
        }
        binding.btnCurtir.setOnClickListener {
            try {
                viewModelCurtida.curtida.vagaId = vaga.id
                viewModelCurtida.curtida.candidatoId = candidatoId
                viewModelCurtida.curtida.dataCadastro = LocalDateTime.now().toString()
            } catch (e: Exception){}
            viewModelCurtida.salvar()
        }
        binding.btnSeguir.setOnClickListener {
            try {
                viewModelSeguir.seguir.empresaId = vaga.empresaId
                viewModelSeguir.seguir.candidatoId = candidatoId
                viewModelSeguir.seguir.dataCadastro = LocalDateTime.now().toString()
            } catch (e: Exception){}
            viewModelSeguir.salvar()
        }
        return binding.root
    }
}