package com.example.ecuchange.presentacion

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ecuchange.R
import com.example.ecuchange.adapters.ProductsAdapter
import com.example.ecuchange.databinding.ActivityLoginBinding
import com.example.ecuchange.databinding.FragmentListarBinding
import com.example.ecuchange.entities.Products
import com.example.ecuchange.logica.ProductsLogica
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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
        binding.progressBar.visibility = View.VISIBLE

        lifecycleScope.launch(Dispatchers.Main)
        {
            val items = withContext(Dispatchers.IO) {
                ProductsLogica().getProductsList()
            }
            binding.listRecyclerView.layoutManager = LinearLayoutManager(binding.listRecyclerView.context)
            binding.listRecyclerView.adapter = ProductsAdapter(items)
            binding.progressBar.visibility = View.GONE
        }
    }


}