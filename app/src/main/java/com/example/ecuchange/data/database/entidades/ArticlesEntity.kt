package com.example.adoptame.database.entidades

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Entity(tableName = "news")
@Serializable
data class ArticlesEntity(
    @PrimaryKey(autoGenerate = true)
    val id: String?,
    val titulo: String?,
    val descripcion: String?,
    val imagen: String?
)
