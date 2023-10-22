package br.dev.codelabs.lidacarreiras.ui.experiencia

import android.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import br.dev.codelabs.lidacarreiras.data.model.Experiencia
import br.dev.codelabs.lidacarreiras.databinding.FragmentItemExperienciaBinding
import br.dev.codelabs.lidacarreiras.ui.experiencia.ExperienciasFragmentDirections

class ExperienciasAdapter(private val experiencias: List<Experiencia>, val viewModel: ExperienciaViewModel) : RecyclerView.Adapter<ExperienciasAdapter.ExperienciaViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : ExperienciaViewHolder {
        return ExperienciaViewHolder(FragmentItemExperienciaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ExperienciaViewHolder, position: Int) {
        val experiencia = experiencias[position]

        holder.txtEmpresa.text = experiencia.empresa
        holder.txtCargo.text = experiencia.cargo
        holder.itemView.setOnClickListener { view ->
            viewModel.editar(experiencia)
            val action = ExperienciasFragmentDirections.actionExperienciaDados()
            view.findNavController().navigate(action)
        }
        holder.itemView.setOnLongClickListener { view ->
            AlertDialog.Builder(view.context)
                .setMessage("EXCLUIR?")
                .setPositiveButton("Confirmar") { dialog, id ->
                    viewModel.excluir(experiencia)
                }
                .setNegativeButton("CANCELAR") { dialog, id ->
                }.create().show()
            true
        }
    }

    override fun getItemCount(): Int = experiencias.size

    inner class ExperienciaViewHolder(binding: FragmentItemExperienciaBinding) : RecyclerView.ViewHolder(binding.root) {
        val txtEmpresa: TextView = binding.txtEmpresa
        val txtCargo: TextView = binding.txtCargo
    }
}