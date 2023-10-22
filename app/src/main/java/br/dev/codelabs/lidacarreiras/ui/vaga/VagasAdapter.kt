package br.dev.codelabs.lidacarreiras.ui.vaga

import android.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import br.dev.codelabs.lidacarreiras.R
import br.dev.codelabs.lidacarreiras.data.model.Vaga
import br.dev.codelabs.lidacarreiras.databinding.FragmentItemVagaBinding
import coil.load
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

class VagasAdapter(private val vagas: List<Vaga>, val viewModel: VagaViewModel) : RecyclerView.Adapter<VagasAdapter.VagaViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : VagaViewHolder {
        return VagaViewHolder(FragmentItemVagaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: VagaViewHolder, position: Int) {
        val vaga = vagas[position]

        holder.imgFoto.load(R.drawable.semfoto)
        Firebase.storage.getReference(vaga.imagem).downloadUrl.addOnSuccessListener { imageUrl ->
            holder.imgFoto.load(imageUrl)
        }
        holder.txtTitulo.text = vaga.titulo
        //holder.txtEmpresaId.text = vaga.empresaId
        holder.txtDescricao.text = vaga.descricao
        holder.itemView.setOnClickListener { view ->
            viewModel.editar(vaga)
            val action = VagasFragmentDirections.actionDetalheVaga()
            view.findNavController().navigate(action)
        }
        holder.itemView.setOnLongClickListener { view ->
            AlertDialog.Builder(view.context)
                .setMessage("EXCLUIR?")
                .setPositiveButton("Confirmar") { dialog, id ->
                    viewModel.excluir(vaga)
                }
                .setNegativeButton("CANCELAR") { dialog, id ->
                }.create().show()
            true
        }
    }

    override fun getItemCount(): Int = vagas.size

    inner class VagaViewHolder(binding: FragmentItemVagaBinding) : RecyclerView.ViewHolder(binding.root) {
        val imgFoto: ImageView = binding.imgFoto
        val txtTitulo: TextView = binding.txtTitulo
//        val txtEmpresaId: TextView = binding.txtEmpresaId
        val txtDescricao: TextView = binding.txtDescricao
    }
}