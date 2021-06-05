package com.example.itsapp.view.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.itsapp.R
import com.example.itsapp.view.adapter.NewsAdapter


class NewsFragment : Fragment() {

    private lateinit var mRecyclerView:RecyclerView
    private lateinit var mAdapter:NewsAdapter
    private lateinit var mLayoutManager:RecyclerView.LayoutManager
    private lateinit var thread:Thread
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
        //return super.onCreateView(inflater, container, savedInstanceState)
        return view
    }
}