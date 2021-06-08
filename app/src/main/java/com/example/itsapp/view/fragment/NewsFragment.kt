package com.example.itsapp.view.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.itsapp.R
import com.example.itsapp.model.vo.ResultGetSearchNews
import com.example.itsapp.retrofit.NaverApi
import com.example.itsapp.view.adapter.NewsAdapter
import retrofit2.Call
import retrofit2.Callback


class NewsFragment : Fragment() {

    private val naverApi = NaverApi.create()
    private lateinit var recyclerView:RecyclerView
    companion object{
        const val TAG : String = "로그"
        fun newInstance() : NewsFragment{
            return NewsFragment()
        }
    }

    // 메모리에 올라갔을때
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "NewsFragment -onCreate() called")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d(TAG, "NewsFragment -onAttach() called")
    }

    // 뷰가 생성됐을 때
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_news,container,false)
        recyclerView = view.findViewById(R.id.category_news)
        var layoutManager = LinearLayoutManager(view.context,LinearLayoutManager.VERTICAL,false)
        recyclerView.layoutManager = layoutManager
        searchNews("맥북",1,100)
        return view
    }

    fun searchNews(query:String, start:Int,display:Int){
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