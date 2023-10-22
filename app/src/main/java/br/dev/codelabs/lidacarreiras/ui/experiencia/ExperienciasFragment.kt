package br.dev.codelabs.lidacarreiras.ui.experiencia

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import br.dev.codelabs.lidacarreiras.databinding.FragmentListExperienciasBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ExperienciasFragment : Fragment() {
    private var columnCount = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val viewModel : ExperienciaViewModel by activityViewModels()
        val binding = FragmentListExperienciasBinding.inflate(layoutInflater)
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.experiencias.collect{ experiencias ->
                    if (binding.root is RecyclerView) {
                        val recyclerView = binding.root
                        with(recyclerView) {
                            layoutManager = LinearLayoutManager(context)
                            adapter = ExperienciasAdapter(experiencias,viewModel)
                        }
                    }
                }
            }
        }
        return binding.root
    }

    companion object {
        const val ARG_COLUMN_COUNT = "column-count"

        @JvmStatic
        fun newInstance(columnCount: Int) = ExperienciasFragment().apply {
            arguments = Bundle().apply {
                putInt(ARG_COLUMN_COUNT, columnCount)
            }
        }
    }
}