package com.example.ecuchange.data.api.service

import com.example.ecuchange.data.api.entidades.ArticulosEntity
import com.example.ecuchange.data.api.entidades.User
import com.example.ecuchange.data.api.entidades.UsuariosEntity
import com.example.ecuchange.entities.UsuarioModal
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface UsuarioService {

    @POST("/api/usuarios")
    //fun add(@Body userData: UsuarioModal): Call<UsuarioModal>
    suspend fun createEmployee(@Body requestBody: UsuarioModal): Response<UsuarioModal>

    @GET
    suspend fun getAllUsuarios(@Url url:String): Response<UsuariosEntity>

    @GET
    suspend fun getOneUser(@Url url:String): Response<User>
}