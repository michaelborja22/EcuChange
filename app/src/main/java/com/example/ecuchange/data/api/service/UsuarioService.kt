package com.example.ecuchange.data.api.service

import com.example.ecuchange.data.api.entidades.User
import com.example.ecuchange.data.api.entidades.UsuariosEntity
import com.example.ecuchange.entities.UsuarioModal
import retrofit2.Response
import retrofit2.http.*

interface UsuarioService {

    @POST("/api/usuarios")
    suspend fun createEmployee(@Body requestBody: UsuarioModal): Response<UsuarioModal>

    @GET
    suspend fun getAllUsuarios(@Url url:String): Response<UsuariosEntity>

    @GET
    suspend fun getOneUser(@Url url:String): Response<User>

    @PUT("/api/usuario/{id}")
    suspend fun editarUsuario(@Path("id")  id: String, @Body requestBody: UsuarioModal): Response<UsuarioModal>
}