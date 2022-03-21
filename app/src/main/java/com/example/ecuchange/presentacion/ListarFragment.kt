package com.example.ecuchange.presentacion

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.SearchView
import android.content.Context
import android.content.Intent
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
import kotlinx.coroutines.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer

class ListarFragment : Fragment(), SearchView.OnQueryTextListener {

private lateinit var binding: FragmentListarBinding

    private var category: String = "6212ef2448b036d3701843e7"
    private lateinit var oneUser: UsuarioEntity
    val e: MutableList<String> = mutableListOf()
    private lateinit var v:View
    private lateinit var  lista: List<ArticlesEntity>
    var par:Boolean=true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentListarBinding.inflate(inflater,container,false)
        v= inflater.inflate(R.layout.fragment_listar, container, false)



        val user = arrayOf("Abhay","Joseph","Maria","Avni","Apoorva","Chris","David","Kaira","Dwayne","Christopher",
            "Jim","Russel","Donald","Brack","Vladimir")

        val userAdapter : ArrayAdapter<String> = ArrayAdapter(
            v.context,android.R.layout.simple_list_item_1,e
        )

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

                    clear()
                    loadArticulos(category)
                    for(i in 1..lista.size){
                        println("Contando $i")
                    }
                }

                override fun onTabReselected(tab: TabLayout.Tab?) {}
                override fun onTabUnselected(tab: TabLayout.Tab?) {}
            }
        )
    }


    fun loadArticulos(category: String) {
        binding.listRecyclerView.clearAnimation()
        binding.progressBar.visibility = View.VISIBLE

        GlobalScope.launch(Dispatchers.Main) { var items = withContext(Dispatchers.Main) {
                ProductsLogica().getProductsList(category)
            }

            if(items.size%2==1){
                lista=items
                par=false
                val entrees: MutableList<ArticlesEntity> = mutableListOf()

                entrees.addAll(items)
                entrees.add(ArticlesEntity("","ARTICULO","","https://img.clasf.co/2020/06/14/Chevrolet-optra-2007-1-4-20200614132958.5596560015.jpg",0, ""))
                items=entrees
            }else{
                par=true
            }

            binding.listRecyclerView.layoutManager = LinearLayoutManager(binding.listRecyclerView.context)
            //Cuando la lista es impar se debe crear un articulo extra para que se mande al view holder y asi mostrar
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

    override fun onQueryTextSubmit(p0: String?): Boolean {
        TODO("Not yet implemented")
    }

    override fun onQueryTextChange(p0: String?): Boolean {
        TODO("Not yet implemented")
    }


}


