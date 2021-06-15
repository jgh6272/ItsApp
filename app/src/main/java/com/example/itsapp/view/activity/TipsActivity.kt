package com.example.itsapp.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.itsapp.R
import com.example.itsapp.view.adapter.BlogAdapter
import com.example.itsapp.viewmodel.NewsViewModel
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_tips.*

class TipsActivity : AppCompatActivity() {

    private val viewModel: NewsViewModel by viewModels()
    private lateinit var blogRecyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tips)

        btnEvent()
        rvEvent()
    }
    fun rvEvent(){
        blogRecyclerView = findViewById(R.id.tips_rv)
        var BlogLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
        blogRecyclerView.layoutManager = BlogLayoutManager
        viewModel.searchReadBlog("맥북 리뷰",1,100)

        viewModel.blogLiveData.observe(this, Observer {
            var result = it.items
            val mAdapter = BlogAdapter(result,this)
            blogRecyclerView.adapter = mAdapter
        })
    }
    fun btnEvent(){
        back_btn.setOnClickListener{
            finish()
        }
        tips_tab.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener{
            override fun onTabReselected(p0: TabLayout.Tab?) {}
            override fun onTabUnselected(p0: TabLayout.Tab?) {}
            override fun onTabSelected(p0: TabLayout.Tab?) {
                val position = p0?.position
                when(position){
                    0 -> {
                        viewModel.searchReadBlog("맥북 리뷰",1,100)
                    }
                    1 -> {
                        viewModel.searchReadBlog("삼성 노트북 리뷰",1,100)
                    }
                    2 -> {
                        viewModel.searchReadBlog("LG 노트북 리뷰",1,100)
                    }
                    3 -> {
                        viewModel.searchReadBlog("ASUS 노트북 리뷰",1,100)
                    }
                    4 -> {
                        viewModel.searchReadBlog("DELL 노트북 리뷰",1,100)
                    }
                    5 -> {
                        viewModel.searchReadBlog("LENOVO 노트북 리뷰",1,100)
                    }
                }
            }
        })
    }
}