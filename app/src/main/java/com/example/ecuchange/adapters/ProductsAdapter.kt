package com.example.ecuchange.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ecuchange.R
import com.example.ecuchange.databinding.ItemProductsListBinding
import com.example.ecuchange.entities.Products
import com.squareup.picasso.Picasso

class ProductsAdapter(val productsItemsList: List<Products>):RecyclerView.Adapter<ProductsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsViewHolder {
        var layoutInflater = LayoutInflater.from(parent.context)
        val view =layoutInflater.inflate(R.layout.item_products_list,parent,false)
        return ProductsViewHolder(view)
    }

    //Asigna cada uno de los elementos al viewholder
    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {
        val item= productsItemsList[position]
        holder.render(item)
    }

    override fun getItemCount(): Int = productsItemsList.size

}

class ProductsViewHolder(productsView: View) : RecyclerView.ViewHolder(productsView) {

    val binding = ItemProductsListBinding.bind(productsView)

    fun render(item: Products){
        print("\n\nid: "+item.id+" Nombre: "+item.nombre+"      "+item.img)
        binding.txtTitulo.text=item.nombre
        binding.txtDescripcion.text=item.descripcion

       Picasso.get().load(item.img).into(binding.imagenProducto)

    }

}
