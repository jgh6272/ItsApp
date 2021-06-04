package com.example.itsapp.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.itsapp.model.vo.DeviceInfo
import com.example.itsapp.retrofit.APIInterface
import com.example.itsapp.retrofit.RetrofitClient
import com.example.itsapp.util.SharedPreference
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class DeviceViewModel(application: Application): AndroidViewModel(application) {
    val context = getApplication<Application>().applicationContext
    val service: APIInterface = RetrofitClient.getInstance(context).create(
        APIInterface::class.java)
    val prefs = SharedPreference(application)

    val deviceNameLiveData = MutableLiveData<String>()
    val deviceLiveData = MutableLiveData<DeviceInfo?>()

    fun getDeviceInfo(deviceName: String){
        viewModelScope.launch {
            val data = service.getDeviceInfo(deviceName)
            deviceNameLiveData.value = data

        }
    }
    fun getDevice(){
//        service.getDevice().enqueue(object : Callback<DeviceInfo> {
//            override fun onResponse(call: Call<DeviceInfo>, response: Response<DeviceInfo>) {
//                Log.d("Response : ",response.body().toString())
//                val result : DeviceInfo? = response.body()
//                deviceLiveData.postValue(result)
//            }
//            override fun onFailure(call: Call<DeviceInfo>, t: Throwable) {
//
//            }
//
//        })

    }
}