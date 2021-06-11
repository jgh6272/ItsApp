package com.example.itsapp.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.itsapp.model.vo.DeviceInfo
import com.example.itsapp.model.vo.ReviewInfo
import com.example.itsapp.retrofit.APIInterface
import com.example.itsapp.retrofit.RetrofitClient
import kotlinx.coroutines.launch

class ReviewViewModel(application: Application): AndroidViewModel(application) {
    val context = getApplication<Application>().applicationContext
    val service: APIInterface = RetrofitClient.getInstance(context).create(
        APIInterface::class.java)

    val reviewLiveData = MutableLiveData<ReviewInfo>()

    fun getReviewThird(deviewName: String){
        viewModelScope.launch {
            val data:ReviewInfo = service.getReviewThird(deviewName)
            reviewLiveData.value = data
            Log.d("getReviewInfo",data.jsonArray.toString())
        }
    }
}