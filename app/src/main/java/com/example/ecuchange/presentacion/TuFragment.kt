package com.example.ecuchange.presentacion

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.adoptame.database.entidades.ArticlesEntity
import com.example.ecuchange.data.database.entidades.UsuarioEntity
import com.example.ecuchange.databinding.FragmentListarBinding
import com.example.ecuchange.databinding.FragmentTuBinding
import com.example.ecuchange.logica.UsuarioLogica
import com.example.ecuchange.uploadImagen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class TuFragment : Fragment() {
    private lateinit var binding: FragmentTuBinding
    private lateinit var oneUser: UsuarioEntity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTuBinding.inflate(inflater, container, false)

        var nombreUsuario: String? = this.arguments?.getString("message")
        var usuario: String? = this.arguments?.getString("usuario").toString()
        println("Nombre del usuario"+usuario)

        oneUser = Json.decodeFromString<UsuarioEntity>(this.arguments?.getString("usuario").toString())

        if(oneUser.nombre==null) {
            binding.txtNombrePerfil.setText("Entra o \nRegístrate")
        }else{
            binding.txtNombrePerfil.setText(nombreUsuario)
            binding.botonIrALogin.visibility =View.GONE
        }

        binding.botonEditarPerfil.setOnClickListener() {
            var intent = Intent(activity, InformationUser::class.java)
            println()
            startActivity(intent)
        }

        binding.botonIrALogin.setOnClickListener() {
            var intent = Intent(activity, LoginActivity::class.java)
            println()
            startActivity(intent)
        }

        binding.botonSubirProducto.setOnClickListener() {
            var intent = Intent(activity, SeleccionarCategoria::class.java)
            println()
            startActivity(intent)
        }

        binding.botonVerMisProductos.setOnClickListener() {
            var intent = Intent(activity, MisProductos::class.java)
            println()
            startActivity(intent)
        }

        binding.botonCambiarPassword.setOnClickListener() {
            var intent = Intent(activity, CambioPassword::class.java)
            println()
            startActivity(intent)
        }

        binding.botonCerrarSesion.setOnClickListener() {
            var intent = Intent(activity, LoginActivity::class.java)
            println()
            startActivity(intent)
        }




        return binding.root


    }


}