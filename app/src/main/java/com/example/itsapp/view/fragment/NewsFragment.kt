package com.example.itsapp.view.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.itsapp.R
import com.example.itsapp.view.adapter.BlogAdapter
import com.example.itsapp.view.adapter.CategoryAdapter
import com.example.itsapp.view.adapter.NewsAdapter
import com.example.itsapp.viewmodel.NewsViewModel

class NewsFragment : Fragment() {

    private val viewModel:NewsViewModel by viewModels()
    private lateinit var newsRecyclerView:RecyclerView
    private lateinit var blogRecyclerView:RecyclerView
    private lateinit var partRecyclerView:RecyclerView
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
        recyclerView(view)
        LiveData()
        return view
    }
    fun LiveData(){
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
        viewModel.partLiveData.observe(viewLifecycleOwner, Observer {
            if(it.code.equals("200")){
                partRecyclerView.adapter = CategoryAdapter(it.brand)
            }
        })
    }
    fun recyclerView(view:View){
        partRecyclerView = view.findViewById(R.id.category_news)
        var PartLayoutManager = LinearLayoutManager(view.context,LinearLayoutManager.HORIZONTAL,false)
        partRecyclerView.layoutManager = PartLayoutManager
        viewModel.getPart()

        newsRecyclerView = view.findViewById(R.id.news_rv)
        var NewsLayoutManager = LinearLayoutManager(view.context,LinearLayoutManager.VERTICAL,false)
        newsRecyclerView.layoutManager = NewsLayoutManager
        viewModel.searchReadNews("맥북",1,20)

        blogRecyclerView = view.findViewById(R.id.blog_rv)
        var BlogLayoutManager = LinearLayoutManager(view.context,LinearLayoutManager.VERTICAL,false)
        blogRecyclerView.layoutManager = BlogLayoutManager
        viewModel.searchReadBlog("맥북",1,20)
    }
}