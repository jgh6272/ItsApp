package com.example.itsapp.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.itsapp.model.vo.UserInfo
import com.example.itsapp.retrofit.APIInterface
import com.example.itsapp.retrofit.RetrofitClient
import com.example.itsapp.util.SharedPreference
import kotlinx.coroutines.launch

class LoginViewModel(application: Application): AndroidViewModel(application) {
    val context = getApplication<Application>().applicationContext
    val service: APIInterface = RetrofitClient.getInstance(context).create(
        APIInterface::class.java)
    val prefs = SharedPreference(application)
    val loginLiveData = MutableLiveData<UserInfo>()

    /*유저 정보*/
    fun userInfo(userId:String){
        viewModelScope.launch {
            val data = service.userInfo(userId)
            loginLiveData.value = data
        }
    }
}