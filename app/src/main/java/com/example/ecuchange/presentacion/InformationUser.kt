package com.example.ecuchange.presentacion

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.HashMap


class InformationUser : AppCompatActivity() {
    private lateinit var binding: ActivityInformationUserBinding
    private lateinit var oneUser: UsuarioEntity
    private val File = 1
    private val database = Firebase.database
    val myRef = database.getReference("user")
    private var UrlImagenUser: String =""


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
                Picasso.get().load(oneUser.urlImagen).into(binding.imagenUsuario)

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

        val modal = UsuarioModal(nombre,apellido,correo, user,direccion,UrlImagenUser)
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
        startActivityForResult(intent, File)
        // startForActivityResult.launch(intent)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == File) {
            if (resultCode == RESULT_OK) {
                val FileUri = data!!.data
                println("LA PRIMERA URL " + FileUri)
                binding.imagenUsuario.setImageURI(FileUri)
                val Folder: StorageReference =
                    FirebaseStorage.getInstance().getReference().child("User")
                val file_name: StorageReference = Folder.child("file" + FileUri!!.lastPathSegment)
                file_name.putFile(FileUri).addOnSuccessListener { taskSnapshot ->
                    file_name.getDownloadUrl().addOnSuccessListener { uri ->
                        val hashMap =
                            HashMap<String, String>()
                        hashMap["link"] = java.lang.String.valueOf(uri)
                        UrlImagenUser = java.lang.String.valueOf(uri)
                        println("LA SEGUNDAS URL  " + UrlImagenUser)
                        myRef.setValue(hashMap)
                        Log.d("Mensaje", "Se subió correctamente")
                    }
                }
            }
        }
    }

//    private val startForActivityResult = registerForActivityResult(
//        ActivityResultContracts.StartActivityForResult()
//    ) { result ->
//        if (result.resultCode == Activity.RESULT_OK) {
//            val data = result.data?.data
//            println(data)
//            binding.imagenUsuario.setImageURI(data)
//        }
//    }

}