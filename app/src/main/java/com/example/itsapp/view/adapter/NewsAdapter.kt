package com.example.itsapp.view.adapter

import android.os.Build
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.itsapp.R
import com.example.itsapp.model.vo.Items

class NewsAdapter(private val items: List<Items>) : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val main: TextView
        val title:TextView
        val date:TextView
        init {
            // Define click listener for the ViewHolder's View.
            main = view.findViewById(R.id.news_title_tv)
            title = view.findViewById(R.id.news_main_tv)
            date = view.findViewById(R.id.news_date_tv)
        }
        fun setItem(item:Items){
            main.setText(item.description.htmlToString())
            title.setText(item.title.htmlToString())
            date.setText(item.pubDate)
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
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.news_item, viewGroup, false)
        return ViewHolder(view)
    }
    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
       var item:Items = items.get(position)
        viewHolder.setItem(item)
    }
    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = items.size
}