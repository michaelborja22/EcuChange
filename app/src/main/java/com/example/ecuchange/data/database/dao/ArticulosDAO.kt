package com.example.ecuchange.data.database.dao

import androidx.room.*
import com.example.adoptame.database.entidades.ArticlesEntity

@Dao
interface ArticulosDAO {

    @Query("SELECT * FROM articulos")
    suspend fun getAllProducts(): List<ArticlesEntity>

    @Query("SELECT * FROM articulos WHERE id = :idProducts")
    suspend fun getProductsById(idProducts: String): ArticlesEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProducts(products: ArticlesEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateProducts(products: ArticlesEntity)

    @Delete()
    suspend fun deleteOneProduct(products: ArticlesEntity)

    @Query("DELETE FROM articulos")
    suspend fun cleanDbProducts()

    @Query("DELETE FROM articulos WHERE id = :idProducts")
    suspend fun deleteNewsById(idProducts: String)

}