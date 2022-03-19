package com.example.ecuchange.presentacion

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.adoptame.database.entidades.ArticlesEntity
import com.example.ecuchange.R
import com.example.ecuchange.adapters.ProductsAdapter
import com.example.ecuchange.data.database.entidades.UsuarioEntity
import com.example.ecuchange.databinding.FragmentListarBinding
import com.example.ecuchange.entities.Products
import com.example.ecuchange.logica.ProductsLogica
import com.example.ecuchange.logica.UsuarioLogica
import com.example.ecuchange.utils.EnumArticulos
import com.google.android.material.tabs.TabLayout
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer

class ListarFragment : Fragment() {

private lateinit var binding: FragmentListarBinding

    private var category: String = "6212ef2448b036d3701843e7"
    private lateinit var oneUser: UsuarioEntity
    var par:Boolean=true
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentListarBinding.inflate(inflater,container,false)
        var myValue: String? = this.arguments?.getString("message")
        println("AquII ESTOYYY "+myValue)
        println()
        return binding.root
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        loadArticulos(category)
        binding.tabLayout.addOnTabSelectedListener(
            object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    val idCat = tab?.position!!
                    category = EnumArticulos.SelectionCategory.fromPosition(idCat)
                    println(category)
                    clear()
                    loadArticulos(category)
                }

                override fun onTabReselected(tab: TabLayout.Tab?) {}
                override fun onTabUnselected(tab: TabLayout.Tab?) {}
            }
        )
    }


    fun loadArticulos(category: String) {
        binding.listRecyclerView.clearAnimation()
        binding.progressBar.visibility = View.VISIBLE

        lifecycleScope.launch(Dispatchers.Main)
        {
            var items = withContext(Dispatchers.IO) {
                ProductsLogica().getProductsList(category)
            }
            val articulo = withContext(Dispatchers.IO) {
                //ProductsLogica().getOneProduct()
            }

            println(articulo)

            binding.listRecyclerView.layoutManager = LinearLayoutManager(binding.listRecyclerView.context)

            //Cuando la lista es impar se debe crear un articulo extra para que se mande al view holder y asi mostrar
            if(items.size%2==1){
                par=false
                val entrees: MutableList<ArticlesEntity> = mutableListOf()
                entrees.addAll(items)
                entrees.add(ArticlesEntity("","","","https://img.clasf.co/2020/06/14/Chevrolet-optra-2007-1-4-20200614132958.5596560015.jpg",0))
                items=entrees
                println(items[3])
            }else{
                par=true
            }
            binding.listRecyclerView.adapter = ProductsAdapter(items,par){getProductsItem(it)}
            binding.progressBar.visibility = View.GONE
        }
    }

    fun clear() {
        binding.listRecyclerView.adapter = ProductsAdapter(emptyList(),par){getProductsItem(it)}
    }

    private fun getProductsItem(articlesEntity: ArticlesEntity) {
        var i = Intent(activity, ItemActivity::class.java)
        val jsonString = Json.encodeToString(articlesEntity)
        i.putExtra("producto", jsonString)
        startActivity(i)
    }


}