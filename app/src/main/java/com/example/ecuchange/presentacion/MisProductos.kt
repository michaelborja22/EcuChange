package com.example.ecuchange.presentacion

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.adoptame.database.entidades.ArticlesEntity
import com.example.ecuchange.R
import com.example.ecuchange.adapters.ProductsAdapter
import com.example.ecuchange.databinding.ActivityMisProductosBinding
import com.example.ecuchange.databinding.ActivitySeleccionarCategoriaBinding
import com.example.ecuchange.databinding.FragmentListarBinding
import com.example.ecuchange.logica.ProductsLogica
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class MisProductos : AppCompatActivity() {

    private lateinit var binding: ActivityMisProductosBinding
    var par:Boolean=true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMisProductosBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val dbSh = this.getSharedPreferences("dataUser", Context.MODE_PRIVATE)
        var idUsuario = dbSh.getString("id_User", "")

        loadArticulos(idUsuario.toString())

        binding.botonRegresar.setOnClickListener() {
            var intent = Intent(applicationContext, PrincipalActivity::class.java)
            startActivity(intent)
        }
    }

    fun loadArticulos(idUser: String) {
        binding.listRecyclerViewMisProductos.clearAnimation()
        binding.progressBarFav.visibility = View.VISIBLE

        GlobalScope.launch(Dispatchers.Main)
        {
            var items = ProductsLogica().getProductsListbyUser(idUser)


            binding.listRecyclerViewMisProductos.layoutManager = LinearLayoutManager(binding.listRecyclerViewMisProductos.context)

            //Cuando la lista es impar se debe crear un articulo extra para que se mande al view holder y asi mostrar
            if(items.size%2==1){
                par=false
                val entrees: MutableList<ArticlesEntity> = mutableListOf()
                entrees.addAll(items)
                entrees.add(ArticlesEntity("","","","https://img.clasf.co/2020/06/14/Chevrolet-optra-2007-1-4-20200614132958.5596560015.jpg",0, ""))
                items=entrees
                println(items[3])
            }else{
                par=true
            }
            binding.listRecyclerViewMisProductos.adapter = ProductsAdapter(items,par){getProductsItem(it)}
            binding.progressBarFav.visibility = View.GONE
        }
    }

    private fun getProductsItem(articlesEntity: ArticlesEntity) {
        var i = Intent(this, ItemActivity::class.java)
        val jsonString = Json.encodeToString(articlesEntity)
        i.putExtra("producto", jsonString)
        i.putExtra("isMyArticles", true)
        startActivity(i)
    }
}