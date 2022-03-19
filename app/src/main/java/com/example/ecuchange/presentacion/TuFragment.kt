package com.example.ecuchange.presentacion

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ecuchange.data.database.entidades.UsuarioEntity
import com.example.ecuchange.databinding.FragmentListarBinding
import com.example.ecuchange.databinding.FragmentTuBinding
import com.example.ecuchange.logica.UsuarioLogica
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TuFragment : Fragment() {
    private lateinit var binding: FragmentTuBinding
    private lateinit var oneUser: UsuarioEntity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTuBinding.inflate(inflater, container, false)

        var nombreUsuario: String? = this.arguments?.getString("message")
        //println("Nombre del usuario"+nombreUsuario)

        binding.txtNombrePerfil.setText(nombreUsuario)

        binding.botonEditarPerfil.setOnClickListener() {

            var intent = Intent(activity, InformationUser::class.java)
            println()
            startActivity(intent)
        }

        return binding.root


    }


}