package com.example.ecuchange.data.api.entidades

import com.example.adoptame.database.entidades.ArticlesEntity
import com.example.ecuchange.data.database.entidades.CategoryEntity

data class Category(
    val __v: Int,
    val _id: String,
    val descripcion: String,
    val nombre: String
)

fun Category.toCategoryEntity() = CategoryEntity(_id,nombre,descripcion)