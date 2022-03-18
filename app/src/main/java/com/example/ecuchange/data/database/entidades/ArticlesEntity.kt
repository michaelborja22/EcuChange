package com.example.adoptame.database.entidades

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Entity(tableName = "articulos")
@Serializable
data class ArticlesEntity(
    @PrimaryKey
    val id: String,
    val titulo: String?,
    val descripcion: String?,
    val imagen: String?,
    val precio: Int?,
)
