package com.example.ecuchange.presentacion

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.adoptame.database.entidades.ArticlesEntity
import com.example.ecuchange.adapters.ProductsAdapter
import com.example.ecuchange.databinding.FragmentListarBinding
import com.example.ecuchange.logica.ProductsLogica
import com.example.ecuchange.utils.EnumArticulos
import com.google.android.material.tabs.TabLayout
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class ListarFragment : Fragment() {

private lateinit var binding: FragmentListarBinding

    private var category: String = "6212ef2448b036d3701843e7"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentListarBinding.inflate(inflater,container,false)

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
            val items = withContext(Dispatchers.IO) {
                ProductsLogica().getProductsList(category)
            }
            binding.listRecyclerView.layoutManager = LinearLayoutManager(binding.listRecyclerView.context)
            binding.listRecyclerView.adapter = ProductsAdapter(items){getProductsItem(it)}
            binding.progressBar.visibility = View.GONE
        }
    }

    fun clear() {
        binding.listRecyclerView.adapter = ProductsAdapter(emptyList()){getProductsItem(it)}
    }

    private fun getProductsItem(articlesEntity: ArticlesEntity) {
        var intent = Intent(binding.listRecyclerView.context, ItemActivity::class.java)
        intent.putExtra("producto",articlesEntity.toString())
        startActivity(intent)
    }


}