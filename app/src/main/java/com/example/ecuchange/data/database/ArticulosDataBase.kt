package com.example.ecuchange.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.adoptame.database.entidades.ArticlesEntity
import com.example.ecuchange.data.database.dao.ArticulosDAO

@Database(
    entities = [ArticlesEntity::class],
    version = 1)
abstract class ArticulosDataBase : RoomDatabase() {

    abstract fun articulosDao():ArticulosDAO
}