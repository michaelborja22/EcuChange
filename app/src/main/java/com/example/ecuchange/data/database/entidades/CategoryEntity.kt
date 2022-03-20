package com.example.ecuchange.data.database.entidades

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Entity(tableName = "news")
@Serializable
data class CategoryEntity (
    @PrimaryKey
    val id: String,
    val nombre: String?,
    val descripcion: String?,
)
