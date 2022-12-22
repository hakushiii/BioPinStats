package com.example.biopinstats.database

import androidx.lifecycle.*
import com.example.biopinstats.database.dao.LogDao
import com.example.biopinstats.database.models.Log
import kotlinx.coroutines.flow.Flow

class LogViewModel(private val logDao: LogDao): ViewModel() {

    private val _log = MutableLiveData<String>()
    val log: LiveData<String> = _log

    private val _time = MutableLiveData<String>()
    val time: LiveData<String> = _time

    private val _status = MutableLiveData<String>()
    val status: LiveData<String> = _status

    fun getLog(id: Int): Flow<Log> = logDao.getLog(id)

    fun getAllLog(): Flow<List<Log>> = logDao.getAll()

    private fun insertLogToDao(log: Log) {
        logDao.insertLog(log)
    }

    private fun createEntries(time: String, status: String): Log {
        return Log(
            Time = time,
            Status = status
        )
    }

    fun addLog(time: String, status: String) {
        val newLog = createEntries(time, status)
        insertLogToDao(newLog)
    }

    fun isEntryValid(Time: String, Status: String): Boolean {
        if (Time.isBlank())
            return false
        else if (Status.isBlank())
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