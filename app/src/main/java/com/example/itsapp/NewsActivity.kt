package com.example.itsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.itsapp.view.adapter.NewsAdapter
import com.example.itsapp.viewmodel.NewsViewModel

class NewsActivity : AppCompatActivity() {
    private val viewModel: NewsViewModel by viewModels()
    private lateinit var newsRecyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)


        newsRecyclerView = findViewById(R.id.news_rv_activity)
        var NewsLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
        newsRecyclerView.layoutManager = NewsLayoutManager
        viewModel.searchReadNews("맥북",1,100)
        viewModel.newsLiveData.observe(this, Observer {
            var result = it.items
            val mAdapter = NewsAdapter(result,this)
            newsRecyclerView.adapter = mAdapter
        })
    }
}