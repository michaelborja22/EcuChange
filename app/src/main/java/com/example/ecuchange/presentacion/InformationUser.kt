package com.example.ecuchange.presentacion

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import com.example.adoptame.database.entidades.ArticlesEntity
import com.example.ecuchange.R
import com.example.ecuchange.data.api.RetrofitAPI
import com.example.ecuchange.data.api.service.UsuarioService
import com.example.ecuchange.data.database.entidades.UsuarioEntity
import com.example.ecuchange.databinding.ActivityInformationUserBinding
import com.example.ecuchange.databinding.ActivityLoginBinding
import com.example.ecuchange.entities.UsuarioModal
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


            val dbSh = this.getSharedPreferences("dataUser", Context.MODE_PRIVATE)
            var id = dbSh.getString("id_User", "")
            println(id)
            CoroutineScope(Dispatchers.Main).launch {
                // access = UsuarioLogica().LoginUser(binding.txtEmail.text.toString(),binding.txtPassword.text.toString())
                oneUser = UsuarioLogica().getOneUser(id.toString())
                binding.txtUsuario.setText(oneUser.user)
                binding.txtApellido.setText(oneUser.apellido)
                binding.txtNombre.setText(oneUser.nombre)
                binding.txtEmail.setText(oneUser.correo)
                binding.txtDireccion.setText(oneUser.direccion)

        }

        binding.botonCargarImagen.setOnClickListener() {
            requestPermission()
        }

        binding.botonGuardarUsuario.setOnClickListener() {
            println("Entre al metodo de guardar")
            CoroutineScope(Dispatchers.IO).launch {
                updateData(binding.txtNombre.text.toString(), binding.txtApellido.text.toString(), binding.txtEmail.text.toString(), binding.txtUsuario.text.toString(), binding.txtDireccion.text.toString())
            }
        }

        binding.botonRegresar.setOnClickListener() {
            var intent = Intent(this, PrincipalActivity::class.java )
            startActivity(intent)
        }

    }

    suspend fun updateData (nombre: String, apellido: String, correo: String, user: String, direccion: String) {

        val modal = UsuarioModal(nombre,apellido,correo, user,direccion)
        val service = RetrofitAPI.postUsuariosApi().create(UsuarioService::class.java)
        val dbSh = this.getSharedPreferences("dataUser", Context.MODE_PRIVATE)
        var id = dbSh.getString("id_User", "")
        println(modal)
        val response = service.editarUsuario(id.toString(), modal)

        if (response.isSuccessful) {
            var intent = Intent(this, PrincipalActivity::class.java )
            startActivity(intent)
        } else {
            Toast.makeText(this,"Retrofit Error ${response.code().toString()}", Toast.LENGTH_SHORT).show()
        }
    }

    private fun requestPermission() {
        // Verificaremos el nivel de API para solicitar los permisos
        // en tiempo de ejecución
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            when {

                ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                ) == PackageManager.PERMISSION_GRANTED -> {
                    pickPhotoFromGallery()
                }

                else -> requestPermissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
            }
        } else {
            // Se llamará a la función para APIs 22 o inferior
            // Esto debido a que se aceptaron los permisos
            // al momento de instalar la aplicación
            pickPhotoFromGallery()
        }
    }

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->

        if (isGranted) {
            pickPhotoFromGallery()
        } else {
            Toast.makeText(
                this,
                "Permission denied",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun pickPhotoFromGallery() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        startForActivityResult.launch(intent)
    }

    private val startForActivityResult = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data = result.data?.data
            println(data)
            binding.imagenUsuario.setImageURI(data)
        }
    }

}