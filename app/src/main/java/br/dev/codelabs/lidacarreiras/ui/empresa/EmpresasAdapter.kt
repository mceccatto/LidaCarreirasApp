package br.dev.codelabs.lidacarreiras.ui.empresa

import android.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import br.dev.codelabs.lidacarreiras.R
import br.dev.codelabs.lidacarreiras.data.model.Empresa
import br.dev.codelabs.lidacarreiras.databinding.FragmentItemEmpresaBinding
import br.dev.codelabs.lidacarreiras.ui.empresa.EmpresasFragmentDirections
import coil.load
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

class EmpresasAdapter(private val empresas: List<Empresa>, val viewModel: EmpresaViewModel) : RecyclerView.Adapter<EmpresasAdapter.EmpresaViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : EmpresaViewHolder {
        return EmpresaViewHolder(FragmentItemEmpresaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: EmpresaViewHolder, position: Int) {
        val empresa = empresas[position]

        holder.imgFoto.load(R.drawable.semfoto)
        Firebase.storage.getReference(empresa.imagem).downloadUrl.addOnSuccessListener { imageUrl ->
            holder.imgFoto.load(imageUrl)
        }
        holder.txtRasaoSocial.text = empresa.rasaoSocial
        holder.txtNomeFantasia.text = empresa.nomeFantasia
        holder.itemView.setOnClickListener { view ->
            viewModel.editar(empresa)
            val action = EmpresasFragmentDirections.actionEmpresaDados()
            view.findNavController().navigate(action)
        }
        holder.itemView.setOnLongClickListener { view ->
            AlertDialog.Builder(view.context)
                .setMessage("EXCLUIR?")
                .setPositiveButton("Confirmar") { dialog, id ->
                    viewModel.excluir(empresa)
                }
                .setNegativeButton("CANCELAR") { dialog, id ->
                }.create().show()
            true
        }
    }

    override fun getItemCount(): Int = empresas.size

    inner class EmpresaViewHolder(binding: FragmentItemEmpresaBinding) : RecyclerView.ViewHolder(binding.root) {
        val imgFoto: ImageView = binding.imgFoto
        val txtRasaoSocial: TextView = binding.txtRasaoSocial
        val txtNomeFantasia: TextView = binding.txtNomeFantasia
    }
}