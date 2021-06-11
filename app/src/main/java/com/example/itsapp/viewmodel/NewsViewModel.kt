package com.example.itsapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.itsapp.model.vo.Blog
import com.example.itsapp.model.vo.BrandPart
import com.example.itsapp.model.vo.News
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
    val partLiveData = MutableLiveData<BrandPart>()

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
    fun getPart(){
        viewModelScope.launch {
            val data = service. getBrand()
            partLiveData.value =data
        }
    }
}
