package com.example.itsapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.itsapp.model.vo.DeviceInfo
import com.example.itsapp.model.vo.ReviewInfo
import com.example.itsapp.model.vo.User
import com.example.itsapp.model.vo.UserInfo
import com.example.itsapp.retrofit.APIInterface
import com.example.itsapp.retrofit.RetrofitClient
import kotlinx.coroutines.launch

class ReviewViewModel(application: Application): AndroidViewModel(application) {
    val context = getApplication<Application>().applicationContext
    val service: APIInterface = RetrofitClient.getInstance(context).create(
        APIInterface::class.java)

    val reviewLiveData = MutableLiveData<ReviewInfo>()
    val reviewPointCountLiveData = MutableLiveData<DeviceInfo>()
    val writeReviewLiveData = MutableLiveData<String>()
    val deleteReviewLiveData = MutableLiveData<String>()
    val loginUserIdLiveData = MutableLiveData<UserInfo>()
    val checkReviewLiveData = MutableLiveData<String>()

    fun getReviewAll(deviceName : String){
        viewModelScope.launch {
            val data:ReviewInfo = service.getReviewAll(deviceName)
            reviewLiveData.value = data
        }
    }

    fun getReviewThird(deviewName: String){
        viewModelScope.launch {
            val data:ReviewInfo = service.getReviewThird(deviewName)
            reviewLiveData.value = data
            //Log.d("getReviewInfo",data.jsonArray.toString())
        }
    }

    fun getChoiceReview(deviceName: String, writer : String){
        viewModelScope.launch {
            val data:ReviewInfo = service.getChoiceReview(deviceName, writer)
            reviewLiveData.value = data
        }
    }

    fun getReviewPointCount(deviceName : String){
        viewModelScope.launch {
            val data:DeviceInfo = service.getReviewPointCount(deviceName)
            reviewPointCountLiveData.value = data
            //Log.d("getReviewPointCountInfo",data.jsonArray.toString())
        }
    }
    fun writeReview(deviceName: String, userId: String, reviewPoint: Int, contentPros: String, contentCons: String){
        viewModelScope.launch {
            val data:String = service.writeReview(deviceName, userId, reviewPoint, contentPros, contentCons)
            writeReviewLiveData.value = data
        }
    }
    fun getLoginUserId(userId : String){
        viewModelScope.launch {
            val data: UserInfo = service.getLoginUserId(userId)
            loginUserIdLiveData.value = data
        }
    }
    fun deleteReview(deviceName: String, writer: String){
        viewModelScope.launch {
            val data:String = service.deleteReview(deviceName, writer)
            deleteReviewLiveData.value = data
        }
    }
}