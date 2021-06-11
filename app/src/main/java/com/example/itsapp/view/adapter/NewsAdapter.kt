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
import com.example.itsapp.model.vo.NewsItems
import com.example.itsapp.view.activity.ShowIssueActivity

class NewsAdapter(private val items: List<NewsItems>,
                  getActivity: Activity
) : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    private val getActivity:Activity = getActivity
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val main: TextView
        val title:TextView
        val date:TextView
        val cardview: CardView
        init {
            // Define click listener for the ViewHolder's View.
            cardview = view.findViewById(R.id.news_cardview)
            main = view.findViewById(R.id.news_title_tv)
            title = view.findViewById(R.id.news_main_tv)
            date = view.findViewById(R.id.news_date_tv)
        }
        fun setItem(item:NewsItems){
            main.text=item.description.htmlToString()
            title.text = item.title.htmlToString()
            date.text = item.pubDate
        }
        fun String.htmlToString() : String {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                return Html.fromHtml(this, Html.FROM_HTML_MODE_LEGACY).toString()
            } else {
                return Html.fromHtml(this).toString()
            }
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.issue_item, viewGroup, false)
        return ViewHolder(view)
    }
    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        var item:NewsItems = items.get(position)
        viewHolder.setItem(item)
        viewHolder.cardview.setOnClickListener {
            itemClickEvent(item)
        }
    }
    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = items.size
    fun itemClickEvent(item:NewsItems){
        val intent = Intent(getActivity,ShowIssueActivity::class.java)
        intent.putExtra("url",item.originallink)
        getActivity.startActivity(intent,ActivityOptions.makeSceneTransitionAnimation(getActivity).toBundle())
    }
}