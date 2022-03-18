package com.example.ecuchange.data.api.service

import com.example.ecuchange.data.api.entidades.ArticulosEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface ArticulosService {

    @GET
    suspend fun getAllArticulosbyCategoria(@Url url:String): Response<ArticulosEntity>

    @GET
    suspend fun getOneArticulo(@Url url:String): Response<ArticulosEntity>
}