package com.example.ecuchange.data.api.service;

import com.example.ecuchange.entities.UsuarioModal;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.Call;

public interface UserService {

    @POST("users")
    Call<UsuarioModal> createPost(@Body UsuarioModal dataModal);

}
