package com.example.ecuchange.logica

import com.example.adoptame.database.entidades.ArticlesEntity
import com.example.ecuchange.casosDeUso.ProductoUserCase
import com.example.ecuchange.entities.Products

class ProductsLogica {

    suspend fun getProductsList(category: String):List<ArticlesEntity>{
        return ProductoUserCase().getAllProducts(category)
    }


    suspend fun checkIsSaved(id: String): Boolean {
        val n = ProductoUserCase().getOneFavoriteProduct(id)
        return n != null
    }



    suspend fun getOneProduct(id : String): ArticlesEntity{
      return ProductoUserCase().getOneProduct(id)
  }

    suspend fun saveFavNews(news: ArticlesEntity) {
        ProductoUserCase().saveNewFavNews(news)
    }

    suspend fun deleteNewFavNews(news: ArticlesEntity) {
        ProductoUserCase().deleteNewFavNews(news)
    }

}