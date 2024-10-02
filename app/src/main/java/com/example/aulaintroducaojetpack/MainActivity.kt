package com.example.aulaintroducaojetpack

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.aulaintroducaojetpack.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private lateinit var mainViewModel: MainViewModel
    private lateinit var dataBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)


        //DataBinding
        dataBinding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_main
        )

        //dado
        val usuario = Usuario("Anderson", "28")

        dataBinding.usuario = usuario
        dataBinding.clique = EventoClique(this)

        // mainViewModel = MainViewModel()

        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        /*val textoContador = mainViewModel.recuperarContador()
        binding.textResultado.text = "cliques: $textoContador"*/


        //Observador
        val liveData = mainViewModel.recuperarLiveData()
        liveData.observe(this) { contador ->
            binding.textResultado.text = "cliques: $contador"
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

       /* with(binding) {
            btnClique.setOnClickListener {
                mainViewModel.incrementarContador()
                //  textResultado.text = "Contador:  ${mainViewModel.recuperarContador()}"
            }
        }*/
    }
    inner class EventoClique(
        private val context: Context
    ){
        fun executarAcao(view: View){
            Toast.makeText(context, "Botão clicado", Toast.LENGTH_LONG ).show()
        }
    }

}// fim da classe