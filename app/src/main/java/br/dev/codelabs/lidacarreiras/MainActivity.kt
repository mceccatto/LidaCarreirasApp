package br.dev.codelabs.lidacarreiras

import android.os.Bundle
import android.view.Menu
import androidx.activity.viewModels
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import br.dev.codelabs.lidacarreiras.databinding.ActivityMainBinding
import br.dev.codelabs.lidacarreiras.ui.candidato.CandidatoViewModel
import br.dev.codelabs.lidacarreiras.ui.empresa.EmpresaViewModel
import br.dev.codelabs.lidacarreiras.ui.vaga.VagaViewModel
import br.dev.codelabs.lidacarreiras.ui.vaga.VagasFragmentDirections
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModelVaga : VagaViewModel by viewModels()
        val viewModelCandidato : CandidatoViewModel by viewModels()
        val viewModelEmpresa : EmpresaViewModel by viewModels()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.toolbar)

        /*binding.appBarMain.adicionarCandidato.setOnClickListener {
                view -> viewModelVaga.novo()
            val action = VagasFragmentDirections.actionListaVagasFragmentToVagaDetalheFragment()
            findNavController(R.id.nav_host_fragment_content_main).navigate(action)
        }

        binding.appBarMain.adicionarCandidato.setOnClickListener {
                view -> viewModelCandidato.novo()
            val action = VagasFragmentDirections.actionListaVagasFragmentToVagaDetalheFragment()
            findNavController(R.id.nav_host_fragment_content_main).navigate(action)
        }

        binding.appBarMain.adicionarEmpresa.setOnClickListener {
                view -> viewModelEmpresa.novo()
            val action = VagasFragmentDirections.actionListaVagasFragmentToVagaDetalheFragment()
            findNavController(R.id.nav_host_fragment_content_main).navigate(action)
        }*/

        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)

        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.listaVagasFragment, R.id.cadastroCandidatoFragment, R.id.listarCandidatosFragment, R.id.cadastroEmpresaFragment, R.id.listarEmpresasFragment), drawerLayout
        )

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}