package com.example.ecuchange.presentacion

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.adoptame.database.entidades.ArticlesEntity
import com.example.ecuchange.R
import com.example.ecuchange.data.api.RetrofitAPI
import com.example.ecuchange.data.api.service.ArticulosService
import com.example.ecuchange.data.api.service.UsuarioService
import com.example.ecuchange.data.database.entidades.UsuarioEntity
import com.example.ecuchange.databinding.ActivityItemBinding
import com.example.ecuchange.databinding.ActivityLoginBinding
import com.example.ecuchange.entities.Products
import com.example.ecuchange.logica.ProductsLogica
import com.example.ecuchange.logica.UsuarioLogica
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import com.squareup.picasso.Picasso
import kotlinx.coroutines.*


class ItemActivity : AppCompatActivity() {

        private lateinit var binding: ActivityItemBinding

        private var fav: Boolean = false
        private lateinit var oneUser: UsuarioEntity
        private lateinit var articuloItem: ArticlesEntity
        private var isMyArticles: Boolean = false

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            binding = ActivityItemBinding.inflate(layoutInflater)
            setContentView(binding.root)

            var n: ArticlesEntity? = null
            intent.extras?.let{
               articuloItem = Json.decodeFromString<ArticlesEntity>(it.getString("producto").toString())

                isMyArticles = it.getBoolean("isMyArticles")

                println(isMyArticles)
                println("ARTICULO ESCOGIDO: "+articuloItem)
            }

            var id = articuloItem.idUsuario
            println(id)
            CoroutineScope(Dispatchers.Main).launch {
                oneUser = UsuarioLogica().getOneUser(id.toString())
                Picasso.get().load(oneUser.urlImagen).into(binding.imagenUsuario)
                binding.txtUsuario.text = oneUser.user
                binding.txtTelefono.text = "Telefono: ${oneUser.telefono}"

            }

            if (articuloItem != null) {
                loadNews(articuloItem!!)
            }

            if (isMyArticles) {
                binding.linearLayout.visibility = View.GONE
                binding.botonLikeItem.visibility = View.GONE
                binding.botonComprarItem.visibility = View.GONE
                binding.botonIntercambiarItem.visibility = View.GONE
                binding.botonEliminar.visibility = View.VISIBLE
            } else {
                binding.botonLikeItem.setOnClickListener() {
                    saveFavNews(articuloItem)
                }
            }

            binding.botonEliminar.setOnClickListener() {
                println("Eliminando Producto")
                CoroutineScope(Dispatchers.IO).launch {
                    deleteProduct()
                }
            }
            binding.botonComprarItem.setOnClickListener(){
                var sendIntent = Intent()
                sendIntent.setAction(Intent.ACTION_VIEW)
                var uri="whatsapp://send?phone=593"+oneUser.telefono+"&text="+"HOLA! Me interesaria comprar el articulo" +
                        ": "+articuloItem.titulo+", publicado en ECUCHANGE"
                sendIntent.setData(Uri.parse(uri))
                startActivity(sendIntent);
            }
            binding.botonIntercambiarItem.setOnClickListener(){
                var sendIntent = Intent()
                sendIntent.setAction(Intent.ACTION_VIEW)
                var uri="whatsapp://send?phone=593"+oneUser.telefono+"&text="+"HOLA! Me interesaria intercambiar el articulo" +
                        ": "+articuloItem.titulo+", publicado en ECUCHANGE"
                sendIntent.setData(Uri.parse(uri))
                startActivity(sendIntent);
            }
            }

    suspend fun deleteProduct () {
        val service = RetrofitAPI.deleteArticuloApi().create(ArticulosService::class.java)
        val response = service.eliminarProducto(articuloItem.id)
        if (response) {
                var intent = Intent(this, MisProductos::class.java )
            startActivity(intent)
        } else {
            Toast.makeText(this,"Retrofit Error ${response}", Toast.LENGTH_SHORT).show()
        }
    }
    private fun loadNews(articlesEntity: ArticlesEntity) {
        binding.tituloItem.text = articuloItem.titulo
        binding.descripcionItem.text = articuloItem.descripcion
        Picasso.get().load(articuloItem.imagen).into(binding.imagenProducto)

        lifecycleScope.launch(Dispatchers.Main) {
            fav = withContext(Dispatchers.IO) { ProductsLogica().checkIsSaved(articuloItem.id) }
            if (fav) {
                binding.botonLikeItem.setImageResource(R.drawable.corazon32)
            }
        }
    }

    private fun saveFavNews(articulo: ArticlesEntity?) {
        if (articulo != null) {
            if (!fav) {
                lifecycleScope.launch {
                    ProductsLogica().saveFavNews(articulo)
                    binding.botonLikeItem.setImageResource(R.drawable.corazon32)

                    Picasso.get().load(articuloItem.imagen).into(binding.imagenProducto)
                   // println("ARTICULO AÑADIDO: " +articulo)
                    fav =ProductsLogica().checkIsSaved(articuloItem.id)
                }
            } else {
                lifecycleScope.launch {
                    ProductsLogica().deleteNewFavNews(articulo)
                    //println("ARTICULO ElIMINADO: " +articulo)
                    binding.botonLikeItem.setImageResource(R.drawable.corazon_adoptame)
                    fav =ProductsLogica().checkIsSaved(articuloItem.id)
                }
            }
        }
    }


        }
