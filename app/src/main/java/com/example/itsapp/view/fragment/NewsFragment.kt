package com.example.itsapp.view.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.itsapp.R
import com.example.itsapp.view.activity.NewsActivity
import com.example.itsapp.view.adapter.BlogAdapter
import com.example.itsapp.view.adapter.NewsAdapter
import com.example.itsapp.viewmodel.NewsViewModel
import kotlinx.android.synthetic.main.fragment_news.*

class NewsFragment : Fragment() {

    private val viewModel: NewsViewModel by viewModels()
    private lateinit var newsRecyclerView: RecyclerView
    private lateinit var blogRecyclerView: RecyclerView
    private var userAge:String =""
    private var userSex:String =""
    private var userJob:String = ""
    companion object{
        fun newInstance() : NewsFragment {
            return NewsFragment()
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView(view)
        LiveData(view)
        btnEvent(view)
        viewModel.surveyParticipation()
    }

    fun LiveData(view:View){
        viewModel.newsLiveData.observe(viewLifecycleOwner, Observer {
            var result = it.items
            val mAdapter = this!!.activity?.let { it1 -> NewsAdapter(result, it1) }
            newsRecyclerView.adapter = mAdapter
        })
        viewModel.blogLiveData.observe(viewLifecycleOwner, Observer {
            var result = it.items
            val mAdapter = this!!.activity?.let { it1 -> BlogAdapter(result, it1) }
            blogRecyclerView.adapter = mAdapter
        })
        viewModel.participationLiveData.observe(viewLifecycleOwner, Observer {
            if(it.code == "204"){
                if(it.jsonArray.participation=="y"){
                    userAge = it.jsonArray.userAge
                    userJob = it.jsonArray.userJob
                    userSex = it.jsonArray.userSex
                }
            }else {

            }
        })
    }
    fun recyclerView(view:View){
        newsRecyclerView = view.findViewById(R.id.news_rv)
        var NewsLayoutManager = LinearLayoutManager(view.context, LinearLayoutManager.VERTICAL,false)
        newsRecyclerView.layoutManager = NewsLayoutManager
        viewModel.searchReadNews("노트북 이슈",1,3)

        /*blogRecyclerView = view.findViewById(R.id.blog_rv)
        var BlogLayoutManager = LinearLayoutManager(view.context, LinearLayoutManager.VERTICAL,false)
        blogRecyclerView.layoutManager = BlogLayoutManager
        viewModel.searchReadBlog("노트북",1,3)*/
    }
    fun btnEvent(view: View){
        recent_news_tv.setOnClickListener{
            val intent = Intent(view.context,
                NewsActivity::class.java)
            startActivity(intent)
        }
        /*tips_tv.setOnClickListener{
            val intent = Intent(view.context,
                TipsActivity::class.java)
            startActivity(intent)
        }*/
    }
}