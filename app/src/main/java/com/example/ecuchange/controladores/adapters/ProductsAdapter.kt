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
        //print("Tamaño de lista: "+productsItemsList.size+"\n")
       // print("\nTITULO: "+item.titulo+"\n")
        if(((productsItemsList.size/2)+position-1)<productsItemsList.size-1) {
            val item2 = productsItemsList[(productsItemsList.size / 2) + position ]

            holder.render(item,item2,onClickItemSelected)
            for (i in 0..productsItemsList.size) {
            }
        }

    }

    override fun getItemCount(): Int = productsItemsList.size/2

}

class ProductsViewHolder(productsView: View) : RecyclerView.ViewHolder(productsView) {

    private val binding: ItemProductsListBinding = ItemProductsListBinding.bind(productsView)

    fun render(item: ArticlesEntity,item2: ArticlesEntity,onClickItemSelected: (ArticlesEntity) -> Unit){
        binding.txtTitulo.text=item.titulo
        binding.precio.text=item.precio.toString()
        binding.txtTitulo2.text=item2.titulo
        binding.precio2.text=item2.precio.toString()
       Picasso.get().load(item.imagen).into(binding.imagenProducto)
        Picasso.get().load(item2.imagen).into(binding.imagenProducto2)
        binding.cardView3.setOnClickListener {
            onClickItemSelected(item2)
        }
        binding.cardView2.setOnClickListener {
            onClickItemSelected(item)
        }

    }

}
