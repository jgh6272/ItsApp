package com.example.itsapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.itsapp.APIInterface
import com.example.itsapp.RetrofitClient
import kotlinx.coroutines.launch

class JoinViewModel:ViewModel() {
    val joinLiveData = MutableLiveData<String>()

    val service: APIInterface = RetrofitClient.getInstance().create(APIInterface::class.java)

    fun join(userId : String, userPw : String , userName : String, userNickname: String, loginMethod:String){
        viewModelScope.launch {
            val data = service.join(userId,userPw,userName,userNickname,loginMethod)
            joinLiveData.value = data
        }
    }
}