package com.example.ecuchange.presentacion

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.ecuchange.R
import com.example.ecuchange.data.api.RetrofitAPI
import com.example.ecuchange.data.api.service.ArticulosService
import com.example.ecuchange.data.api.service.UsuarioService
import com.example.ecuchange.databinding.ActivityProductoRegisterBinding
import com.example.ecuchange.databinding.ActivitySeleccionarCategoriaBinding
import com.example.ecuchange.entities.ProductoModal
import com.example.ecuchange.entities.UsuarioModal
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductoRegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProductoRegisterBinding
    private lateinit var estado: String

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

        binding.botonRegistrar.setOnClickListener() {
            val dbSh = this.getSharedPreferences("dataUser", Context.MODE_PRIVATE)
            var idCategoria = dbSh.getString("category_product", "")
            var idUsuario = dbSh.getString("id_User", "")
            var imagen = "https://i0.wp.com/hipertextual.com/wp-content/uploads/2020/11/portada-ps5--scaled.jpg?fit=1200%2C800&ssl=1"
            if (binding.titulo.text.isEmpty() || binding.descripcion.text.isEmpty() || binding.precio.text.isEmpty() || binding.ciudad.text.isEmpty() || binding.direccion.text.isEmpty()) {
                Toast.makeText(this,"Complete los campos",
                    Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            CoroutineScope(Dispatchers.IO).launch {
                postData(binding.titulo.text.toString(),binding.descripcion.text.toString(),idCategoria.toString(), binding.precio.text.toString(), binding.ciudad.text.toString(), binding.direccion.text.toString(), estado, imagen,idUsuario.toString() )
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
}