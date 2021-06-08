package com.example.itsapp.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.example.itsapp.model.vo.ResultGetSearchNews
import com.example.itsapp.retrofit.NaverApi
import com.example.itsapp.view.adapter.NewsAdapter
import retrofit2.Call
import retrofit2.Callback

class NewsViewModel:ViewModel() {
    val naverApi = NaverApi.create()

    fun searchNews(query:String, start:Int,display:Int, recyclerView: RecyclerView){
        naverApi.getSearchNews(query,start,display)
            .enqueue(object : Callback<ResultGetSearchNews> {
                override fun onResponse(
                    call: Call<ResultGetSearchNews>,
                    response: retrofit2.Response<ResultGetSearchNews>
                ){
                    var result = response.body()
                    var itemResult = result!!.items
                    val mAdapter = NewsAdapter(itemResult)
                    recyclerView.adapter = mAdapter
                    Log.d("네이버 Api", "onResponse: $response.toString()")
                }
                override fun onFailure(call: Call<ResultGetSearchNews>, t: Throwable) {
                    Log.d("네이버 Api", "onFailure: $t")
                }
            })
    }
}
