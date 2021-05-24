package com.example.itsapp.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.itsapp.retrofit.APIInterface
import com.example.itsapp.retrofit.RetrofitClient
import com.example.itsapp.util.SharedPreference
import kotlinx.coroutines.launch

class HomeViewModel(application: Application): AndroidViewModel(application){
    val context = getApplication<Application>().applicationContext
    val prefs:SharedPreference = SharedPreference(context)
    val service: APIInterface = RetrofitClient.getInstance(context).create(
        APIInterface::class.java)
    val userIdLiveData = MutableLiveData<String>()
    val secondJoinLiveData = MutableLiveData<String>()

    fun getLoginSession():String{
        var userSession = ""
        val iterator = prefs.getCookies()?.iterator()
        if(iterator!=null){
            while(iterator.hasNext()){
                userSession =iterator.next()
                userSession = userSession.split(";")[0].split("=")[1]
                Log.d("userInfo", "getLoginSession: $userSession")
            }
        }
        userIdLiveData.postValue(userSession)
        return userSession
    }

    fun seceondJoin(userId:String, loginMethod:String){
        viewModelScope.launch {
            val data = service.seceondJoin(userId,loginMethod)
            secondJoinLiveData.value = data
        }
    }
}