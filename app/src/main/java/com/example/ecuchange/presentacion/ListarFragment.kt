package com.example.ecuchange.presentacion

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ecuchange.R
import com.example.ecuchange.adapters.ProductsAdapter
import com.example.ecuchange.databinding.ActivityLoginBinding
import com.example.ecuchange.databinding.FragmentListarBinding
import com.example.ecuchange.entities.Products
import com.example.ecuchange.logica.ProductsLogica

class ListarFragment : Fragment() {

private lateinit var binding: FragmentListarBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentListarBinding.inflate(inflater,container,false)

        binding.listRecyclerView.adapter = ProductsAdapter(ProductsLogica().getProductsList())
        binding.listRecyclerView.layoutManager = LinearLayoutManager(binding.listRecyclerView.context)

        return binding.root
    }

}