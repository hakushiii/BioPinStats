package com.example.biopinstats

import android.app.Application
import com.example.biopinstats.database.AppDatabase

class MainApp: Application() {
    val database: AppDatabase by lazy {
        AppDatabase.getDatabase(this)
    }
}