package com.example.itsapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.itsapp.retrofit.APIInterface
import com.example.itsapp.retrofit.RetrofitClient
import kotlinx.coroutines.launch

class JoinViewModel:ViewModel() {
    val service: APIInterface = RetrofitClient.getInstance().create(
        APIInterface::class.java)
    val joinLiveData = MutableLiveData<String>()
    val checkIdLiveData = MutableLiveData<String>()

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
}