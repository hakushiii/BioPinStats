package com.example.biopinstats.main.api

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
                val result = CommandApi.retrofitService.getData()
                    _command.value = result.command

            }
            catch (e: Exception) {
                Log.d("API","Error: ${e.message}")
            }
        }
    }
}