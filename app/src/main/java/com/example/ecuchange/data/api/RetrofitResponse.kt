package com.example.ecuchange.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitResponse {

    private val BASE_URL_PROYECT = "https://ecu-change.herokuapp.com/"

    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://ecu-change.herokuapp.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}