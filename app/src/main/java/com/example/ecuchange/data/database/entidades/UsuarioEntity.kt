package com.example.ecuchange.data.database.entidades

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Entity(tableName = "news")
@Serializable
data class UsuarioEntity (
    @PrimaryKey(autoGenerate = true)
    val id: String?,
    val user: String?,
    val password: String?,
    val correo: String?,
    val nombre: String? = null ,
    val apellido: String? = null ,
    val direccion: String? = null ,
) {

}