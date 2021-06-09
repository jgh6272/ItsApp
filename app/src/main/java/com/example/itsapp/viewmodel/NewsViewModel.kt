package com.example.itsapp.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.RecyclerView
import com.example.itsapp.model.vo.ResultGetSearchNews
import com.example.itsapp.retrofit.NaverApi
import com.example.itsapp.view.adapter.NewsAdapter
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback

class NewsViewModel:ViewModel() {
    val naverApi = NaverApi.create()
    val newsLiveData = MutableLiveData<ResultGetSearchNews>()

    fun searchReadNews(query:String, start:Int,display:Int, recyclerView: RecyclerView){
        /*viewModelScope.launch : viewmodel lifecycle안에 있을때 사용하겠다.*/
        viewModelScope.launch {
            val data = naverApi.getSearchNews(query,start,display)
            newsLiveData.value = data
        }
    }
}
