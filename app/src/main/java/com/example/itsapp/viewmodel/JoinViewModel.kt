package com.example.itsapp.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.itsapp.APIInterface
import kotlinx.coroutines.launch

class JoinViewModel @ViewModelInject constructor(
    private val service : APIInterface
):ViewModel() {
    val joinLiveData = MutableLiveData<String>()

    fun join(userId : String, userPw : String , userName : String, userNickname: String, loginMethod:String){
        viewModelScope.launch {
            val data = service.join(userId,userPw,userName,userNickname,loginMethod)
            joinLiveData.value = data
        }
    }
}