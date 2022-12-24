package com.example.biopinstats.main.api

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ApiViewModel: ViewModel() {

    private val _command = MutableLiveData<String>()
    val command: LiveData<String> = _command

    init {
        getApiData()
    }

    private fun getApiData() {
        viewModelScope.launch {
            try {
                while (true) {
                    val result = CommandApi.retrofitService.getData()
                    _command.value = result.command
                    delay(2500)
                }
            }
            catch (e: Exception) {
                Log.d("API","Error: ${e.message}")
            }
        }
    }
}