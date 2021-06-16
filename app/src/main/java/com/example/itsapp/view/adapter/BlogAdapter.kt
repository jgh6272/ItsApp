package com.example.itsapp.view.adapter

import android.app.Activity
import android.app.ActivityOptions
import android.content.Intent
import android.os.Build
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.itsapp.R
import com.example.itsapp.model.vo.BlogItems
import com.example.itsapp.view.activity.ShowIssueActivity

class BlogAdapter(private val item: List<BlogItems>, getActivity:Activity) : RecyclerView.Adapter<BlogAdapter.ViewHolder>() {
    private val getActivity :Activity = getActivity
    class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        val main:TextView
        val cardview:CardView
        val title:TextView
        val date :TextView
        init {
            cardview = view.findViewById(R.id.news_cardview)
            main = view.findViewById(R.id.news_title_tv)
            title = view.findViewById(R.id.news_main_tv)
            date = view.findViewById(R.id.news_date_tv)
        }
        fun setItem(item:BlogItems) {
            main.setText(item.description.htmlToString())
            title.setText(item.title.htmlToString())
            date.setText(item.bloggername.htmlToString())
        }
        fun String.htmlToString() : String {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                return Html.fromHtml(this, Html.FROM_HTML_MODE_LEGACY).toString()
            } else {
                return Html.fromHtml(this).toString()
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.issue_item,parent,false)
        return ViewHolder(view)
    }
    override fun getItemCount() = item.size
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var item:BlogItems = item.get(position)
        holder.setItem(item)
        holder.cardview.setOnClickListener {
            itemClickEvent(item)
        }
    }
    private fun itemClickEvent(item:BlogItems){
        val intent = Intent(getActivity,ShowIssueActivity::class.java)
        intent.putExtra("url",item.link)
        getActivity.startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(getActivity).toBundle())
    }
}