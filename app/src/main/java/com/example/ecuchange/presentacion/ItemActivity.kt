package com.example.ecuchange.presentacion

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.example.adoptame.database.entidades.ArticlesEntity
import com.example.ecuchange.R
import com.example.ecuchange.databinding.ActivityItemBinding
import com.example.ecuchange.databinding.ActivityLoginBinding
import com.example.ecuchange.entities.Products
import com.example.ecuchange.logica.ProductsLogica
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class ItemActivity : AppCompatActivity() {

        private lateinit var binding: ActivityItemBinding

        private var fav: Boolean = false
        private lateinit var articuloItem: ArticlesEntity

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            binding = ActivityItemBinding.inflate(layoutInflater)
            setContentView(binding.root)

            var n: ArticlesEntity? = null
            intent.extras?.let{

               articuloItem = Json.decodeFromString<ArticlesEntity>(it.getString("producto").toString())
                println("ARTICULO ESCOGIDO: "+articuloItem)
            }

            if (articuloItem != null) {
                loadNews(articuloItem!!)
            }


            binding.botonLikeItem.setOnClickListener(){
                saveFavNews(articuloItem)
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
                    println("ARTICULO AÑADIDO: " +articulo)
                    fav =ProductsLogica().checkIsSaved(articuloItem.id)
                }
            } else {
                lifecycleScope.launch {
                    ProductsLogica().deleteNewFavNews(articulo)
                    println("ARTICULO ElIMINADO: " +articulo)
                    binding.botonLikeItem.setImageResource(R.drawable.corazon_adoptame)
                    fav =ProductsLogica().checkIsSaved(articuloItem.id)
                }
            }
        }
    }


        }
