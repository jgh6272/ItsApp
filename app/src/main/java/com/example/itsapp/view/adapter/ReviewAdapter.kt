package com.example.itsapp.view.adapter

import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.itsapp.R
import com.example.itsapp.model.vo.Review

class ReviewAdapter(var reviewList:ArrayList<Review>) : RecyclerView.Adapter<ReviewAdapter.ViewHolder>() {

    private lateinit var itemClickListener : OnItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.review_item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.writer.text = reviewList.get(position).writer
        holder.riviewPoint.rating = reviewList.get(position).reviewPoint.toFloat()
        holder.writeTime.text = reviewList.get(position).writeTime
        holder.contentPros.text = reviewList.get(position).contentPros
        holder.contentCons.text = reviewList.get(position).contentCons
        holder.likeCount.text = reviewList.get(position).likeCount.toString()
        holder.commnetCount.text = reviewList.get(position).commentCount.toString()

        holder.itemView.setOnClickListener {
            itemClickListener.onClick(it, position)
        }
    }

    override fun getItemCount(): Int {
        return reviewList.size
    }

    fun updateItem(item: List<Review>){
        reviewList = item as ArrayList<Review>
        notifyDataSetChanged()
    }

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val writer = itemView.findViewById<TextView>(R.id.writer)
        val riviewPoint = itemView.findViewById<RatingBar>(R.id.review_point)
        val writeTime = itemView.findViewById<TextView>(R.id.write_time)
        val contentPros = itemView.findViewById<TextView>(R.id.content_pros)
        val contentCons = itemView.findViewById<TextView>(R.id.content_cons)
        val likeCount = itemView.findViewById<TextView>(R.id.like_count)
        val commnetCount = itemView.findViewById<TextView>(R.id.comment_count)
    }

    interface OnItemClickListener{
        fun onClick(v: View, position: Int)
    }
    fun setItemClickListener(onItemClickListener: OnItemClickListener){
        this.itemClickListener = onItemClickListener
    }
}