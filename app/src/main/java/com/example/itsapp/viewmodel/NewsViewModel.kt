package com.example.itsapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.itsapp.model.vo.Blog
import com.example.itsapp.model.vo.News
import com.example.itsapp.retrofit.NaverApi
import kotlinx.coroutines.launch

class NewsViewModel:ViewModel() {
    val naverApi = NaverApi.create()
    val newsLiveData = MutableLiveData<News>()
    val blogLiveData = MutableLiveData<Blog>()

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
}
