package com.example.biopinstats.database.models

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Log(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @NonNull @ColumnInfo
    val Status: String,
    @ColumnInfo
    val Uptime: String,
    @ColumnInfo
    val Description: String,

)
