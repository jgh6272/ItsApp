package com.example.itsapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.itsapp.retrofit.APIInterface
import com.example.itsapp.retrofit.RetrofitClient
import kotlinx.coroutines.launch

class JoinViewModel(application: Application):AndroidViewModel(application) {
    val context = getApplication<Application>().applicationContext
    val service: APIInterface = RetrofitClient.getInstance(context).create(
        APIInterface::class.java)
    val joinLiveData = MutableLiveData<String>()
    val checkIdLiveData = MutableLiveData<String>()
    val checkNicknameLiveData = MutableLiveData<String>()
    val kakaoLoginLiveData = MutableLiveData<String>()

    fun join(userId : String, userPw : String , userName : String, userNickname: String, loginMethod:String){
        viewModelScope.launch {
            val data = service.join(userId,userPw,userName,userNickname,loginMethod)
            joinLiveData.value = data
        }
    }
    fun checkId(userId:String){
        viewModelScope.launch {
            val data = service.checkId(userId)
            checkIdLiveData.value = data
        }
    }
    fun checkNick(userNickname: String){
        viewModelScope.launch {
            val data = service.checkNick(userNickname)
            checkNicknameLiveData.value = data
        }
    }
    fun kakaoJoin(userId: String,userName:String,userNickname:String){
        viewModelScope.launch {
            val data = service.kakaoLogin(userId,userName,userNickname)
            kakaoLoginLiveData.value = data
        }
    }
}