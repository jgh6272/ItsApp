package com.example.itsapp.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.itsapp.retrofit.APIInterface
import com.example.itsapp.retrofit.RetrofitClient
import com.example.itsapp.util.SharedPreference
import kotlinx.coroutines.launch

class JoinViewModel(application: Application):AndroidViewModel(application) {
    val context = getApplication<Application>().applicationContext
    val service: APIInterface = RetrofitClient.getInstance(context).create(
        APIInterface::class.java)
    val prefs = SharedPreference(application)
    val joinLiveData = MutableLiveData<String>()
    val checkIdLiveData = MutableLiveData<String>()
    val checkNicknameLiveData = MutableLiveData<String>()
    val kakaoLoginLiveData = MutableLiveData<String>()
    val kakaoUserInfoLD = MutableLiveData<String>()
    val userIdLiveData = MutableLiveData<String>()

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
    fun kakaoLogin(userId: String,userName:String){
        viewModelScope.launch {
            val data = service.kakaoLogin(userId,userName)
            kakaoLoginLiveData.value = data
        }
    }
    fun kakaoUserInfo(userId:String, userNickname: String){
        viewModelScope.launch {
            val data = service.kakaoUserInfo(userId,userNickname)
            kakaoUserInfoLD.value =data
        }
    }
    fun getLoginSession():String{
        var userSession = ""
        val iterator = prefs.getCookies()?.iterator()
        if(iterator!=null){
            while(iterator.hasNext()){
                userSession =iterator.next()
                userSession = userSession.split(";")[0].split("=")[1]
                Log.d("userInfo", "getLoginSession: $userSession")
            }
        }else if(iterator==null){
            userIdLiveData.postValue(userSession)
            return userSession
        }
        userIdLiveData.postValue(userSession)
        return userSession
    }
}