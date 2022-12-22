package com.example.biopinstats.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.biopinstats.database.models.Log
import kotlinx.coroutines.flow.Flow

@Dao
interface LogDao {

    @Query("SELECT * FROM Log WHERE id = :id")
    fun getLog(id: Int): Flow<Log>

    @Query("SELECT * FROM Log")
    fun getAll(): Flow<List<Log>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertLog(log: Log)

    @Update
    fun updateLog(log: Log)

    @Delete
    fun deleteLog(log: Log)
}