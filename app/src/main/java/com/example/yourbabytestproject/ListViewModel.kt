package com.example.yourbabytestproject

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ListViewModel : ViewModel() {
    init {
        Log.i("ListViewModel", "ListViewModel created!")
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("ListViewModel", "ListViewModel created!")
    }

    val items: MutableLiveData<List<ListItem>> by lazy {
        MutableLiveData<List<ListItem>>()
    }
}