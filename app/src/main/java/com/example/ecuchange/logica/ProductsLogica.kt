package com.example.ecuchange.logica

import com.example.ecuchange.casosDeUso.ProductoUserCase
import com.example.ecuchange.entities.Products

class ProductsLogica {

    suspend fun getProductsList():List<Products>{
        return ProductoUserCase().getAllProducts()
    }

    suspend fun getOneProduct(): Products{
        val r = (0..2).random()
        print("\nR:" + r+"\n")
        return ProductoUserCase().getAllProducts()[r]
    }

}