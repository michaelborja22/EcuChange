package com.example.ecuchange.data.api.service

import com.example.ecuchange.entities.UsuarioModal
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.Call
import retrofit2.Response

interface UsuarioService {

    @POST("/api/usuarios")
    //fun add(@Body userData: UsuarioModal): Call<UsuarioModal>
    suspend fun createEmployee(@Body requestBody: UsuarioModal): Response<UsuarioModal>
}