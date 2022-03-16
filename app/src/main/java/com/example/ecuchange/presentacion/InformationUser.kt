package com.example.ecuchange.presentacion

import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ecuchange.R
import com.example.ecuchange.data.database.entidades.UsuarioEntity
import com.example.ecuchange.databinding.ActivityInformationUserBinding
import com.example.ecuchange.databinding.ActivityLoginBinding
import com.example.ecuchange.logica.UsuarioLogica
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class InformationUser : AppCompatActivity() {
    private lateinit var binding: ActivityInformationUserBinding
    private lateinit var oneUser: UsuarioEntity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInformationUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //setContentView(R.layout.activity_information_user)

        binding.botonUser.setOnClickListener() {
            val dbSh = this.getSharedPreferences("dataUser", Context.MODE_PRIVATE)
            var id = dbSh.getString("id_User", "")
            println(id)
            CoroutineScope(Dispatchers.Main).launch {
                // access = UsuarioLogica().LoginUser(binding.txtEmail.text.toString(),binding.txtPassword.text.toString())
                oneUser = UsuarioLogica().getOneUser(id.toString())
                println(oneUser)

            }
        }
    }
}