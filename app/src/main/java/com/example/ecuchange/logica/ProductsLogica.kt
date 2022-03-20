package com.example.ecuchange.logica

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.adoptame.database.entidades.ArticlesEntity
import com.example.ecuchange.casosDeUso.ProductoUserCase
import com.example.ecuchange.data.database.entidades.CategoryEntity
import com.example.ecuchange.entities.Products
import kotlinx.coroutines.launch

class ProductsLogica : ViewModel() {

    val retFavNews = MutableLiveData<List<ArticlesEntity>>()
    val retNews = MutableLiveData<List<ArticlesEntity>>()
    val isLoading = MutableLiveData<Boolean>()

    suspend fun getProductsList(category: String):List<ArticlesEntity>{
        return ProductoUserCase().getAllProducts(category)
    }

    suspend fun getCategoriesList():List<CategoryEntity> {
        return ProductoUserCase().getAllCategories()
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

    fun searchFavNews(query: String) {
        viewModelScope.launch {
            isLoading.postValue(true)
            var items: ArrayList<ArticlesEntity> = ArrayList()
            var items1: ArrayList<ArticlesEntity> = ArrayList()
            items = ProductoUserCase().getFavoritesProducts() as ArrayList<ArticlesEntity>
            if (!query.isNullOrBlank()) {
                items.forEach {
                    if (it.titulo?.lowercase()?.contains(query.lowercase()) == true) {
                        items1.add(it)
                    }
                }
                retFavNews.postValue(items1)
            } else {
                retFavNews.postValue(items)
            }
            isLoading.postValue(false)
        }
    }



}