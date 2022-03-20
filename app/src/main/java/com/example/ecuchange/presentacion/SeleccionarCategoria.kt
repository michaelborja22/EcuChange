package com.example.ecuchange.presentacion

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.lifecycle.lifecycleScope
import com.example.adoptame.database.entidades.ArticlesEntity
import com.example.ecuchange.R
import com.example.ecuchange.data.database.entidades.CategoryEntity
import com.example.ecuchange.databinding.ActivityRegisterBinding
import com.example.ecuchange.databinding.ActivitySeleccionarCategoriaBinding
import com.example.ecuchange.databinding.FragmentTuBinding
import com.example.ecuchange.logica.ProductsLogica
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SeleccionarCategoria : AppCompatActivity() {

    private lateinit var binding: ActivitySeleccionarCategoriaBinding
    private lateinit var arrayAdapter: ArrayAdapter<*>
    private lateinit var items: List<CategoryEntity>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySeleccionarCategoriaBinding.inflate(layoutInflater)
        setContentView(binding.root)



         lifecycleScope.launch(Dispatchers.Main)
        {
            items =  ProductsLogica().getCategoriesList()
            var categorias = items.map {
                it.nombre
            }
            println("Estoy esperando las categorias")
            println(items)
            arrayAdapter = ArrayAdapter(applicationContext, android.R.layout.simple_list_item_1, categorias)
            binding.vlCategorias.adapter = arrayAdapter

        }

        binding.vlCategorias.setOnItemClickListener() {parent,view,position,id ->
            val dbSh = this.getSharedPreferences("dataUser", Context.MODE_PRIVATE)
            var editor = dbSh.edit()
            var idCategory = items[id.toInt()].id
            var nameCategory = items[id.toInt()].nombre
            editor.putString("category_product", idCategory)
            editor.commit()
            editor.putString("name_category_product", nameCategory)
            editor.commit()
            var intent = Intent(this, ProductoRegisterActivity::class.java )
            startActivity(intent)
        }

        binding.botonVer.setOnClickListener() {
            val dbSh = this.getSharedPreferences("dataUser", Context.MODE_PRIVATE)
            var id = dbSh.getString("category_product", "")
            var name = dbSh.getString("name_category_product", "")
            println(id)
        }





    }
}