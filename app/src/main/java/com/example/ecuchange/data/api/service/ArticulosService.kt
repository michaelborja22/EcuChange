package com.example.ecuchange.data.api.service

import com.example.ecuchange.data.api.entidades.Article
import com.example.ecuchange.data.api.entidades.ArticulosEntity
import com.example.ecuchange.data.api.entidades.CategoriasEntity
import com.example.ecuchange.entities.ProductoModal
import com.example.ecuchange.entities.UsuarioModal
import retrofit2.Response
import retrofit2.http.*

interface ArticulosService {

    @GET
    suspend fun getAllArticulosbyCategoria(@Url url:String): Response<ArticulosEntity>

    @GET
    suspend fun getAllArticulosbyUser(@Url url:String): Response<ArticulosEntity>

    @GET
    suspend fun getOneArticulo(@Url url:String): Response<Article>

    @GET
    suspend fun getAllCategories(@Url url:String): Response<CategoriasEntity>

    @POST("/api/articulos")
    suspend fun createArticulo(@Body requestBody: ProductoModal): Response<ProductoModal>

    @DELETE("/api/articulo/{id}")
    suspend fun eliminarProducto(@Path("id")  id: String): Response<UsuarioModal>
}