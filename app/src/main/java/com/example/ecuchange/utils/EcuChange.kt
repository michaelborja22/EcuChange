package com.example.ecuchange.utils

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
// import androidx.preference.PreferenceManager
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.ecuchange.data.database.ArticulosDataBase

// import com.example.adoptame.database.NewsDataBase

class EcuChange : Application() {

    override fun onCreate() {
        super.onCreate()
        db = Room.databaseBuilder(applicationContext, ArticulosDataBase::class.java, "articulos_DB")
            .build()
        dbShare = applicationContext.getSharedPreferences("dataUser", Context.MODE_PRIVATE)
        dbPreferences = PreferenceManager.getDefaultSharedPreferences(this)
    }

    companion object {
        private var db: ArticulosDataBase? = null
        private lateinit var dbShare: SharedPreferences
        private lateinit var dbPreferences: SharedPreferences

        fun getShareDB(): SharedPreferences {
            return dbShare
        }

        fun getDatabase(): ArticulosDataBase {
            return db!!
        }

    }


    }

