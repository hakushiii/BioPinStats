package com.example.biopinstats.main.api

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ApiViewModel: ViewModel() {

    init {
        getApiData()
    }

    private fun getApiData() {
        viewModelScope.launch {
            try {
//                val listResult = SignalApi.retrofitService.getSignal()
//                _status.value = "Success: ${listResult.size} Signals retrieved"
//
//                for (signal in listResult) {
//                    _signals.add(Entry(signal.id.toFloat(), signal.data))
//                }
//
//                val lineDataset = LineDataSet(_signals, "Signal")
//                lineDataset.circleRadius = 4f
//                lineDataset.valueTextSize = 10F
//                _data.value = LineData(lineDataset)
            }
            catch (e: Exception) {
//                _status.value = "Failure: ${e.message}"
            }
        }
    }
}