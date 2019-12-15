package com.example.yourbabytestproject

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class ListViewModel : ViewModel() {
    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(
        viewModelJob + Dispatchers.Main
    )

    init {
        Log.i("ListViewModel", "ListViewModel created!")
        getEventInfo()
    }

    private val _response = MutableLiveData<String>()
    private val _property = MutableLiveData<List<EventProperty>>()

    val response: MutableLiveData<String>
        get() = _response
    val property: MutableLiveData<List<EventProperty>>
        get() = _property

    private fun getEventInfo() {
        coroutineScope.launch {
            var getPropertiesDeferred = MockarooApi.retrofitService.getEvents()
            try {
                var listResult = getPropertiesDeferred.await()
                _property.value = listResult
                _response.value =
                    "Success: ${listResult.size} Events properties retrieved"
                Log.i("Internet", "Success: ${listResult.size} Mars properties retrieved")

            } catch (e: Exception) {
                _response.value = "Failure: ${e.message}"
            }

        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}