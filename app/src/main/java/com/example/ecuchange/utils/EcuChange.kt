package com.example.ecuchange.utils

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
// import androidx.preference.PreferenceManager
import androidx.room.Room
import androidx.room.RoomDatabase
// import com.example.adoptame.database.NewsDataBase

class EcuChange : Application() {

    override fun onCreate() {
        super.onCreate()

        dbShare = applicationContext.getSharedPreferences("dataUser", Context.MODE_PRIVATE)

    }

    companion object {
        private lateinit var dbShare: SharedPreferences
    }
        fun getShareDB(): SharedPreferences {
            return dbShare
        }

    }

