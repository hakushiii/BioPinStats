package com.example.biopinstats.database

import androidx.lifecycle.*
import com.example.biopinstats.database.dao.LogDao
import com.example.biopinstats.database.models.Log
import kotlinx.coroutines.flow.Flow

class LogViewModel(private val logDao: LogDao): ViewModel() {

    private val _log = MutableLiveData<String>()
    val log: LiveData<String> = _log

    private val _time = MutableLiveData<String>()
    val uptime: LiveData<String> = _time

    private val _status = MutableLiveData<String>()
    val status: LiveData<String> = _status

    fun getLog(id: Int): Flow<Log> = logDao.getLog(id)

    fun getAllLog(): Flow<List<Log>> = logDao.getAll()

    fun addLog(uptime: String, status: String, description: String) {
        val newLog = Log(
            Status = status,
            Uptime = uptime,
            Description = description)
        logDao.insertLog(newLog)
    }

    fun updateLog(id: Int, uptime: String, status: String, description: String) {
        val newLog = Log(
            id = id,
            Status = status,
            Uptime = uptime,
            Description = description)
        logDao.updateLog(newLog)
    }

    fun deleteLog(log: Log) {
        logDao.deleteLog(log)
    }

    fun isEntryValid(Status: String): Boolean {
        if (Status.isBlank())
            return false
        return true
    }
}

class LogViewModelFactory(private val logDao: LogDao): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LogViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return LogViewModel(logDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}