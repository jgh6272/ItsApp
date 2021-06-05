package com.example.itsapp.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.itsapp.R

class NewsAdapter(private val dataSet: Array<String>) : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val writer: TextView
        val title:TextView
        val image:ImageView
        init {
            // Define click listener for the ViewHolder's View.
            writer = view.findViewById(R.id.news_writer)
            title = view.findViewById(R.id.news_title)
            image = view.findViewById(R.id.news_image)
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
        viewHolder.writer.text = dataSet[position]
        viewHolder.title.text = dataSet[position]
    }
    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size
}