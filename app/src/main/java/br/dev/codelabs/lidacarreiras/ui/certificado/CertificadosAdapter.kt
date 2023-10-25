package br.dev.codelabs.lidacarreiras.ui.certificado

import android.app.AlertDialog
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import br.dev.codelabs.lidacarreiras.R
import br.dev.codelabs.lidacarreiras.data.model.Certificado
import br.dev.codelabs.lidacarreiras.databinding.FragmentItemCertificadoBinding
import coil.load

class CertificadosAdapter(private val certificados: List<Certificado>, val viewModel: CertificadoViewModel) : RecyclerView.Adapter<CertificadosAdapter.CertificadoViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : CertificadoViewHolder {
        return CertificadoViewHolder(FragmentItemCertificadoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: CertificadoViewHolder, position: Int) {
        val certificado = certificados[position]

        if(certificado.arquivo == "") {
            holder.imgFoto.load(R.drawable.semfoto)
        } else {
            holder.imgFoto.setImageBitmap(decodePicString(certificado.arquivo))
        }
        holder.txtInstituicao.text = certificado.instituicao
        holder.txtTitulo.text = certificado.titulo
        holder.itemView.setOnClickListener { view ->
            viewModel.editar(certificado)
            val action = CertificadosFragmentDirections.actionCertificadoDados()
            view.findNavController().navigate(action)
        }
        holder.itemView.setOnLongClickListener { view ->
            AlertDialog.Builder(view.context)
                .setMessage("EXCLUIR?")
                .setPositiveButton("Confirmar") { dialog, id ->
                    viewModel.excluir(certificado)
                }
                .setNegativeButton("CANCELAR") { dialog, id ->
                }.create().show()
            true
        }
    }

    override fun getItemCount(): Int = certificados.size

    inner class CertificadoViewHolder(binding: FragmentItemCertificadoBinding) : RecyclerView.ViewHolder(binding.root) {
        val imgFoto: ImageView = binding.imgFoto
        val txtInstituicao: TextView = binding.txtInstituicao
        val txtTitulo: TextView = binding.txtTitulo
    }

    fun decodePicString (encodedString: String): Bitmap {
        val imageBytes = Base64.decode(encodedString, Base64.DEFAULT)
        val decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
        return decodedImage
    }
}