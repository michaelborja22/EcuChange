package com.example.ecuchange.data.api.entidades

import com.example.adoptame.database.entidades.ArticlesEntity

data class Article(
    val __v: Int,
    val _id: String,
    val categoria: String,
    val ciudad: String,
    val descripcion: String,
    val direccion: String,
    val estado: String,
    val imagen: String,
    val precio: Int,
    val titulo: String,
    val idUsuario: String,
)

fun Article.toArticlesEntity() = ArticlesEntity(_id,titulo,descripcion,imagen, precio, idUsuario)