package com.example.itsapp

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class JoinViewModel : ViewModel() {

    var service : ServiceApi

    init{
        service = RetrofitClient.getClient().create(ServiceApi::class.java)
    }

    var joinRsLD : MutableLiveData<String> = MutableLiveData()

    fun join(userId : String, password : String, userName : String, userNickName : String){
        service.join(userId, password, userName, userNickName)
            .enqueue(object : Callback<String>{
                override fun onResponse(call: Call<String>, response: Response<String>) {
                    val result : String? = response.body()
                    joinRsLD.postValue(result)
                }

                override fun onFailure(call: Call<String>, t: Throwable) {
                    Log.d("Join Errer","통신 오류")
                }
            })
    }
}