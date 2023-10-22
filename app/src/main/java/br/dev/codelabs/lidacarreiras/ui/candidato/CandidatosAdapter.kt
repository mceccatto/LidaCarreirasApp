package br.dev.codelabs.lidacarreiras.ui.candidato

import android.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import br.dev.codelabs.lidacarreiras.R
import br.dev.codelabs.lidacarreiras.data.model.Candidato
import br.dev.codelabs.lidacarreiras.databinding.FragmentItemCandidatoBinding
import br.dev.codelabs.lidacarreiras.ui.candidato.CandidatosFragmentDirections
import coil.load
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

class CandidatosAdapter(private val candidatos: List<Candidato>, val viewModel: CandidatoViewModel) : RecyclerView.Adapter<CandidatosAdapter.CandidatoViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : CandidatoViewHolder {
        return CandidatoViewHolder(FragmentItemCandidatoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: CandidatoViewHolder, position: Int) {
        val candidato = candidatos[position]

        holder.imgFoto.load(R.drawable.semfoto)
        Firebase.storage.getReference(candidato.imagem).downloadUrl.addOnSuccessListener { imageUrl ->
            holder.imgFoto.load(imageUrl)
        }
        holder.txtNome.text = candidato.nome
        holder.txtEmail.text = candidato.email
        holder.itemView.setOnClickListener { view ->
            viewModel.editar(candidato)
            val action = CandidatosFragmentDirections.actionCandidatoDados()
            view.findNavController().navigate(action)
        }
        holder.itemView.setOnLongClickListener { view ->
            AlertDialog.Builder(view.context)
                .setMessage("EXCLUIR?")
                .setPositiveButton("Confirmar") { dialog, id ->
                    viewModel.excluir(candidato)
                }
                .setNegativeButton("CANCELAR") { dialog, id ->
                }.create().show()
            true
        }
    }

    override fun getItemCount(): Int = candidatos.size

    inner class CandidatoViewHolder(binding: FragmentItemCandidatoBinding) : RecyclerView.ViewHolder(binding.root) {
        val imgFoto: ImageView = binding.imgFoto
        val txtNome: TextView = binding.txtNome
        val txtEmail: TextView = binding.txtEmail
    }
}