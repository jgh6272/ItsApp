package com.example.itsapp.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.itsapp.R
import com.example.itsapp.view.adapter.NewsAdapter
import com.example.itsapp.viewmodel.NewsViewModel
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_news.*

class NewsActivity : AppCompatActivity() {
    private val viewModel: NewsViewModel by viewModels()
    private lateinit var newsRecyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)

        btnEvent()
        rvEvent()
    }
    fun rvEvent(){
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
    fun btnEvent(){
        back_btn.setOnClickListener{
            finish()
        }

        news_tab.addOnTabSelectedListener(object:TabLayout.OnTabSelectedListener{
            override fun onTabReselected(p0: TabLayout.Tab?) {}
            override fun onTabUnselected(p0: TabLayout.Tab?) {}
            override fun onTabSelected(p0: TabLayout.Tab?) {
                val position = p0?.position
                when(position){
                    0 -> {
                        viewModel.searchReadNews("맥북",1,100)
                    }
                    1 -> {
                        viewModel.searchReadNews("삼성 노트북",1,100)
                    }
                    2 -> {
                        viewModel.searchReadNews("LG 노트북",1,100)
                    }
                    3 -> {
                        viewModel.searchReadNews("ASUS 노트북",1,100)
                    }
                    4 -> {
                        viewModel.searchReadNews("DELL 노트북",1,100)
                    }
                    5 -> {
                        viewModel.searchReadNews("LENOVO 노트북",1,100)
                    }
                }
            }
        })
    }
}