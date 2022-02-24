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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class ListarFragment : Fragment() {

private lateinit var binding: FragmentListarBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentListarBinding.inflate(inflater,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadArticulos()
    }

    fun loadArticulos() {
        binding.listRecyclerView.clearAnimation()
        lifecycleScope.launch(Dispatchers.Main)
        {
            val items = withContext(Dispatchers.IO) {
                ProductsLogica().getProductsList()
            }
            binding.listRecyclerView.layoutManager = LinearLayoutManager(binding.listRecyclerView.context)
            binding.listRecyclerView.adapter = ProductsAdapter(items){getProductsItem(it)}
        }
    }

    private fun getProductsItem(articlesEntity: ArticlesEntity) {
        var intent = Intent(binding.listRecyclerView.context, ItemActivity::class.java)
        startActivity(intent)
    }


}