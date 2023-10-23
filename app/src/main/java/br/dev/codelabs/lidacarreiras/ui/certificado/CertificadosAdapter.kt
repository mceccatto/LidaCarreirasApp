package br.dev.codelabs.lidacarreiras.ui.certificado

import android.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import br.dev.codelabs.lidacarreiras.data.model.Certificado
import br.dev.codelabs.lidacarreiras.databinding.FragmentItemCertificadoBinding
import br.dev.codelabs.lidacarreiras.ui.certificado.CertificadosFragmentDirections

class CertificadosAdapter(private val certificados: List<Certificado>, val viewModel: CertificadoViewModel) : RecyclerView.Adapter<CertificadosAdapter.CertificadoViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : CertificadoViewHolder {
        return CertificadoViewHolder(FragmentItemCertificadoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: CertificadoViewHolder, position: Int) {
        val certificado = certificados[position]

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
        val txtInstituicao: TextView = binding.txtInstituicao
        val txtTitulo: TextView = binding.txtTitulo
    }
}