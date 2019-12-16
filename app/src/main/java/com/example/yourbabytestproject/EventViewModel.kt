package com.example.yourbabytestproject

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class EventViewModel : ViewModel() {
    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(
        viewModelJob + Dispatchers.Main
    )
    private val _eventInfo = MutableLiveData<EventInfo>()
    private val _response = MutableLiveData<String>()
    val eventInfo: MutableLiveData<EventInfo>
        get() = _eventInfo

    fun getEventInfo(id: Int) {
        coroutineScope.launch {
            var getEventInfoDeferred = MockarooApi.retrofitService.getEventInfoById(id.toString())
            try {
                var resultEventInfo = getEventInfoDeferred.await()
                _eventInfo.value = resultEventInfo

                _response.value =
                    "Success: ${resultEventInfo.id} Events properties retrieved"
                Log.i("Internet", "Success! id: ${resultEventInfo.id} Events properties retrieved")

            } catch (e: Exception) {
                _response.value = "Failure: ${e.message}"
                Log.e("Internet", "Failure: ${e.message}")
            }

        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}