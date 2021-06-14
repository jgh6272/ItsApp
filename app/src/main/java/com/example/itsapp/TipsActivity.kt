package com.example.itsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.itsapp.view.adapter.BlogAdapter
import com.example.itsapp.viewmodel.NewsViewModel

class TipsActivity : AppCompatActivity() {

    private val viewModel: NewsViewModel by viewModels()
    private lateinit var blogRecyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tips)

        blogRecyclerView = findViewById(R.id.tips_rv)
        var BlogLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
        blogRecyclerView.layoutManager = BlogLayoutManager
        viewModel.searchReadBlog("노트북",1,100)

        viewModel.blogLiveData.observe(this, Observer {
            var result = it.items
            val mAdapter = BlogAdapter(result,this)
            blogRecyclerView.adapter = mAdapter
        })
    }
}