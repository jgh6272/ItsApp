package com.example.itsapp.view.fragment

import android.content.Context
import android.content.Intent
import android.graphics.Color
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
import com.example.itsapp.NewsActivity
import com.example.itsapp.R
import com.example.itsapp.TipsActivity
import com.example.itsapp.view.adapter.BlogAdapter
import com.example.itsapp.view.adapter.NewsAdapter
import com.example.itsapp.viewmodel.NewsViewModel
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate
import com.github.mikephil.charting.utils.ColorTemplate.COLORFUL_COLORS
import kotlinx.android.synthetic.main.fragment_issue.*

class IssueFragment : Fragment() {

    private val viewModel:NewsViewModel by viewModels()
    private lateinit var newsRecyclerView:RecyclerView
    private lateinit var blogRecyclerView:RecyclerView
    companion object{
        const val TAG : String = "로그"
        fun newInstance() : IssueFragment{
            return IssueFragment()
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
        val view = inflater.inflate(R.layout.fragment_issue,container,false)
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView(view)
        pieChart(view)
        LiveData()
        btnEvent(view)
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
    }
    fun recyclerView(view:View){
        newsRecyclerView = view.findViewById(R.id.news_rv)
        var NewsLayoutManager = LinearLayoutManager(view.context,LinearLayoutManager.VERTICAL,false)
        newsRecyclerView.layoutManager = NewsLayoutManager
        viewModel.searchReadNews("노트북",1,3)

        blogRecyclerView = view.findViewById(R.id.blog_rv)
        var BlogLayoutManager = LinearLayoutManager(view.context,LinearLayoutManager.VERTICAL,false)
        blogRecyclerView.layoutManager = BlogLayoutManager
        viewModel.searchReadBlog("노트북",1,3)
    }
    fun pieChart(view:View){
        val chart:PieChart = view.findViewById(R.id.trand_chart)
        chart.setUsePercentValues(true)

        //data set
        val entries = ArrayList<PieEntry>()
        entries.add(PieEntry(508f,"LG"))
        entries.add(PieEntry(600f,"Samsung"))
        entries.add(PieEntry(750f,"Apple"))
        entries.add(PieEntry(508f,"DELL"))
        entries.add(PieEntry(335f,"ASUS"))
        entries.add(PieEntry(335f,"LENOVO"))

        val colorsItems = ArrayList<Int>()
        for(c in ColorTemplate.VORDIPLOM_COLORS) colorsItems.add(c)
        for(c in ColorTemplate.JOYFUL_COLORS) colorsItems.add(c)
        for(c in COLORFUL_COLORS) colorsItems.add(c)
        for(c in ColorTemplate.LIBERTY_COLORS) colorsItems.add(c)
        for(c in ColorTemplate.PASTEL_COLORS) colorsItems.add(c)
        colorsItems.add(ColorTemplate.getHoloBlue())

        val pieDataSet = PieDataSet(entries,"")
        pieDataSet.apply {
            colors = colorsItems
            valueTextColor = Color.BLACK
            valueTextSize = 16f
        }
        val pieData = PieData(pieDataSet)
        chart.apply{
            data = pieData
            description.isEnabled = false
            isRotationEnabled = false
            setEntryLabelColor(Color.BLACK)
            animateY(1400, Easing.EaseInOutQuad)
            animate()
        }
    }
    fun btnEvent(view: View){
        recent_news_tv.setOnClickListener{
            val intent = Intent(view.context,NewsActivity::class.java)
            startActivity(intent)
        }
        tips_tv.setOnClickListener{
            val intent = Intent(view.context,TipsActivity::class.java)
            startActivity(intent)
        }
    }
}