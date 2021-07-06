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
import com.example.itsapp.view.activity.TipsActivity
import com.example.itsapp.view.adapter.BlogAdapter
import com.example.itsapp.view.adapter.NewsAdapter
import com.example.itsapp.viewmodel.NewsViewModel
import kotlinx.android.synthetic.main.fragment_rank.*

class RankFragment : Fragment() {

    companion object{
        fun newInstance() : RankFragment {
            return RankFragment()
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
        return inflater.inflate(R.layout.fragment_rank, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}