package com.example.ecuchange.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitAPI {

    fun getArticulosApi(): Retrofit {
        return Retrofit.Builder().baseUrl("https://ecu-change.herokuapp.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}