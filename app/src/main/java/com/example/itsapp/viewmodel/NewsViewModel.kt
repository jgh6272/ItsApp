package com.example.itsapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.itsapp.model.vo.*
import com.example.itsapp.retrofit.APIInterface
import com.example.itsapp.retrofit.NaverApi
import com.example.itsapp.retrofit.RetrofitClient
import kotlinx.coroutines.launch

class NewsViewModel(application: Application):AndroidViewModel(application) {
    val naverApi = NaverApi.create()
    val context = getApplication<Application>().applicationContext
    val service: APIInterface = RetrofitClient.getInstance(context).create(
        APIInterface::class.java)
    val newsLiveData = MutableLiveData<News>()
    val blogLiveData = MutableLiveData<Blog>()
    val deviceLiveData = MutableLiveData<DeviceInfo>()
    val participationLiveData = MutableLiveData<userDetailInfo>()

    fun searchReadNews(query:String, start:Int,display:Int){
        /*viewModelScope.launch : viewmodel lifecycle안에 있을때 사용하겠다.*/
        viewModelScope.launch {
            val data = naverApi.getSearchNews(query,start,display)
            newsLiveData.value = data
        }
    }
    fun searchReadBlog(query:String, start:Int,display:Int){
        /*viewModelScope.launch : viewmodel lifecycle안에 있을때 사용하겠다.*/
        viewModelScope.launch {
            val data = naverApi.getSearchBlog(query,start,display)
            blogLiveData.value = data
        }
    }
    /*fun getReviewCnt(){
        viewModelScope.launch {
            val data  = service.getReviewCnt()
            deviceLiveData.value = data
        }
    }*/
    fun surveyParticipation(){
        viewModelScope.launch {
            val data = service.surveyParticipation()
            participationLiveData.value = data
        }
    }
}
