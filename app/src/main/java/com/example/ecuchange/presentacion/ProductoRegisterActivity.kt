package com.example.ecuchange.presentacion

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import com.example.ecuchange.R
import com.example.ecuchange.data.api.RetrofitAPI
import com.example.ecuchange.data.api.service.ArticulosService
import com.example.ecuchange.data.api.service.UsuarioService
import com.example.ecuchange.databinding.ActivityProductoRegisterBinding
import com.example.ecuchange.databinding.ActivitySeleccionarCategoriaBinding
import com.example.ecuchange.entities.ProductoModal
import com.example.ecuchange.entities.UsuarioModal
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.HashMap

class ProductoRegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProductoRegisterBinding
    private lateinit var estado: String
    private val File = 1
    private val database = Firebase.database
    val myRef = database.getReference("user")
    private var UrlImagenProducto: String =""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductoRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Recupero el nombre de la categoria para mostrarla
        val dbSh = this.getSharedPreferences("dataUser", Context.MODE_PRIVATE)
        var nombreCategoria = dbSh.getString("name_category_product", "")
        binding.categoria.text = nombreCategoria

        // Defino los nombres para en combo box de los estados
        val listaEstados = listOf("Nuevo", "Usado-Como nuevo", "Usado-Buen estado", "Usado-Aceptable")
        val adaptador = ArrayAdapter(this, android.R.layout.simple_spinner_item, listaEstados)
        binding.estado.adapter = adaptador

        binding.botonEditar.setOnClickListener() {
            var intent = Intent(this, SeleccionarCategoria::class.java )
            startActivity(intent)
        }

        binding.botonSubirPro.setOnClickListener() {
            requestPermission()
        }

        binding.botonRegistrar.setOnClickListener() {
            val dbSh = this.getSharedPreferences("dataUser", Context.MODE_PRIVATE)
            var idCategoria = dbSh.getString("category_product", "")
            var idUsuario = dbSh.getString("id_User", "")
            if (binding.titulo.text.isEmpty() || binding.descripcion.text.isEmpty() || binding.precio.text.isEmpty() || binding.ciudad.text.isEmpty() || binding.direccion.text.isEmpty()) {
                Toast.makeText(this,"Complete los campos",
                    Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            CoroutineScope(Dispatchers.IO).launch {
                postData(binding.titulo.text.toString(),binding.descripcion.text.toString(),idCategoria.toString(), binding.precio.text.toString(), binding.ciudad.text.toString(), binding.direccion.text.toString(), estado, UrlImagenProducto,idUsuario.toString() )
            }
        }

        binding.estado.onItemSelectedListener = object:
            AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                estado = listaEstados[p2]
                println(estado)
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
    }

    suspend fun postData (titulo: String, descripcion: String, categoria: String, precio: String, ciudad: String, direccion: String, estado: String, imagen: String, idUsuario: String) {

        val modal = ProductoModal(titulo, descripcion,categoria, precio, ciudad, direccion, estado, imagen, idUsuario)
        val service = RetrofitAPI.postUsuariosApi().create(ArticulosService::class.java)
        val response = service.createArticulo(modal)

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
                binding.imagenProductoSubido.setImageURI(FileUri)
                val Folder: StorageReference =
                    FirebaseStorage.getInstance().getReference().child("Products")
                val file_name: StorageReference = Folder.child("file" + FileUri!!.lastPathSegment)
                file_name.putFile(FileUri).addOnSuccessListener { taskSnapshot ->
                    file_name.getDownloadUrl().addOnSuccessListener { uri ->
                        val hashMap =
                            HashMap<String, String>()
                        hashMap["link"] = java.lang.String.valueOf(uri)
                        UrlImagenProducto = java.lang.String.valueOf(uri)
                        println("LA SEGUNDAS URL  " + UrlImagenProducto)
                        myRef.setValue(hashMap)
                        Log.d("Mensaje", "Se subió correctamente")
                        binding.imagenProductoSubido.setPadding(0,0,0,0)
                    }
                }
            }
        }
    }
}