package com.example.itsapp.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.itsapp.model.vo.UserInfo
import com.example.itsapp.retrofit.APIInterface
import com.example.itsapp.retrofit.RetrofitClient
import kotlinx.coroutines.launch

class LoginViewModel: ViewModel() {
    val service: APIInterface = RetrofitClient.getInstance().create(
        APIInterface::class.java)
    val loginLiveData = MutableLiveData<UserInfo>()

    /*로그인*/
    fun login(userId:String){
        viewModelScope.launch {
            val data = service.login(userId)
            loginLiveData.value = data
        }
    }
}