package com.example.ecuchange.presentacion

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ecuchange.data.database.entidades.UsuarioEntity
import com.example.ecuchange.databinding.FragmentListarBinding
import com.example.ecuchange.databinding.FragmentTuBinding

class TuFragment : Fragment() {
    private lateinit var binding: FragmentTuBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentTuBinding.inflate(inflater,container,false)
        // Inflate the layout for this fragment

        binding.botonEditarPerfil.setOnClickListener() {
            var intent = Intent(activity, InformationUser::class.java)
            println()
            startActivity(intent)
        }

        return binding.root


    }


}