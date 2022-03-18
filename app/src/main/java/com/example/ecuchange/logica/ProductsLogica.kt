package com.example.ecuchange.logica

import com.example.adoptame.database.entidades.ArticlesEntity
import com.example.ecuchange.casosDeUso.ProductoUserCase
import com.example.ecuchange.entities.Products

class ProductsLogica {

    suspend fun getProductsList(category: String):List<ArticlesEntity>{
        return ProductoUserCase().getAllProducts(category)
    }

    /*
    suspend fun checkIsSaved(id: String): Boolean {
        val n = ProductoUserCase().getOneNews(id)
        return n != null
    }
    */


//    suspend fun getOneProduct(): Products{
//        val r = (0..2).random()
//        print("\nR:" + r+"\n")
//        return ProductoUserCase().getAllProducts()[r]
//    }

}