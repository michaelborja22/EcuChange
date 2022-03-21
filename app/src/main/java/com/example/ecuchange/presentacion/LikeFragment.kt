package com.example.ecuchange.presentacion

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.adoptame.database.entidades.ArticlesEntity
import com.example.ecuchange.adapters.ProductsAdapter
import com.example.ecuchange.databinding.FragmentLikeBinding
import com.example.ecuchange.logica.ProductsLogica
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json


class LikeFragment : Fragment() {

    private lateinit var binding: FragmentLikeBinding
    private val productsControllerModel: ProductsLogica by viewModels()
    var par:Boolean=true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLikeBinding.inflate(inflater, container, false)

        // Inflate the layout for this fragment


        return binding.root
    }

    override fun onStart() {
        super.onStart()
        productsControllerModel.searchFavNews("")

         productsControllerModel.retFavNews.observe(viewLifecycleOwner) {
             var productos: List<ArticlesEntity>  = it
            if(productos.size%2==1){
                par=false
                val entrees: MutableList<ArticlesEntity> = mutableListOf()
                entrees.addAll(productos)
                entrees.add(ArticlesEntity("","","","https://img.clasf.co/2020/06/14/Chevrolet-optra-2007-1-4-20200614132958.5596560015.jpg",0, ""))
                productos=entrees
                println(productos[3])
            }else{
                par=true
            }

            binding.progressBarFav.visibility = View.VISIBLE
            lifecycleScope.launch(Dispatchers.Main) {
                binding.listRecyclerViewFav.adapter =
                    ProductsAdapter(productos,par) { getProductsItem(it) }
                binding.listRecyclerViewFav.layoutManager =
                    LinearLayoutManager(binding.listRecyclerViewFav.context)
                binding.progressBarFav.visibility = View.GONE
            }
        }
    }

    private fun getProductsItem(articlesEntity: ArticlesEntity) {
        var i = Intent(activity, ItemActivity::class.java)
        val jsonString = Json.encodeToString(articlesEntity)
        val jsonStringUsuario = Json.encodeToString(articlesEntity)
        i.putExtra("producto", jsonString)
        i.putExtra("usuario", jsonStringUsuario)
        startActivity(i)
    }


}