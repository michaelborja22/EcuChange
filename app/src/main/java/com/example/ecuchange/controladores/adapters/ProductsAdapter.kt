package com.example.ecuchange.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.adoptame.database.entidades.ArticlesEntity
import com.example.ecuchange.R
import com.example.ecuchange.databinding.ItemProductsListBinding
import com.example.ecuchange.entities.Products
import com.squareup.picasso.Picasso

class ProductsAdapter(val productsItemsList: List<ArticlesEntity>,val onClickItemSelected: (ArticlesEntity) -> Unit):
    RecyclerView.Adapter<ProductsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        return ProductsViewHolder(inflater.inflate(R.layout.item_products_list, parent, false))
//        val view =layoutInflater.inflate(R.layout.item_products_list,parent,false)
//        return ProductsViewHolder(view)
    }

    //Asigna cada uno de los elementos al viewholder
    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {
        val item= productsItemsList[position]
        holder.render(item,onClickItemSelected)
    }

    override fun getItemCount(): Int = productsItemsList.size

}

class ProductsViewHolder(productsView: View) : RecyclerView.ViewHolder(productsView) {

    private val binding: ItemProductsListBinding = ItemProductsListBinding.bind(productsView)

    fun render(item: ArticlesEntity,onClickItemSelected: (ArticlesEntity) -> Unit){
        print("\n\nid: "+item.id+" Nombre: "+item.titulo+"      "+item.imagen)
        binding.txtTitulo.text=item.titulo


       Picasso.get().load(item.imagen).into(binding.imagenProducto)

        itemView.setOnClickListener {
            onClickItemSelected(item)
        }


    }

}
