package com.example.itsapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.itsapp.retrofit.APIInterface
import com.example.itsapp.retrofit.RetrofitClient
import com.example.itsapp.util.SharedPreference
import kotlinx.coroutines.launch

class DeviceViewModel(application: Application): AndroidViewModel(application) {
    val context = getApplication<Application>().applicationContext
    val service: APIInterface = RetrofitClient.getInstance(context).create(
        APIInterface::class.java)
    val prefs = SharedPreference(application)

    val deviceNameLiveData = MutableLiveData<String>()

    fun getDeviceInfo(deviceName: String){
        viewModelScope.launch {
            val data = service.getDeviceInfo(deviceName)
            deviceNameLiveData.value = data

        }
    }
}