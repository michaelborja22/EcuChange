package com.example.ecuchange.entities

import kotlinx.serialization.Serializable

@Serializable
data class Products(val id: String, val titulo: String, val descripcion: String, val imagen:String)