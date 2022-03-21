package com.example.ecuchange.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitAPI {

    fun getArticulosApi(): Retrofit {
        return Retrofit.Builder().baseUrl("https://ecu-change.herokuapp.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun postUsuariosApi(): Retrofit {
        return Retrofit.Builder().baseUrl("https://ecu-change.herokuapp.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getUsuariosApi(): Retrofit {
        return Retrofit.Builder().baseUrl("https://ecu-change.herokuapp.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getOneArticulosApi(): Retrofit {
        return Retrofit.Builder().baseUrl("https://ecu-change.herokuapp.com/api/articulo/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun deleteArticuloApi(): Retrofit {
        return Retrofit.Builder().baseUrl("https://ecu-change.herokuapp.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}