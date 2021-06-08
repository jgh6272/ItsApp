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
import com.example.itsapp.viewmodel.NewsViewModel
import retrofit2.Call
import retrofit2.Callback


class NewsFragment : Fragment() {

    private lateinit var recyclerView:RecyclerView
    private val viewModel:NewsViewModel by viewModels()
    companion object{
        const val TAG : String = "로그"
        fun newInstance() : NewsFragment{
            return NewsFragment()
        }
    }
    // 뷰가 생성됐을 때
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_news,container,false)
        recyclerView = view.findViewById(R.id.category_news)
        var layoutManager = LinearLayoutManager(view.context,LinearLayoutManager.VERTICAL,false)
        recyclerView.layoutManager = layoutManager
        viewModel.searchNews("맥북",1,20,recyclerView)
        return view
    }
}