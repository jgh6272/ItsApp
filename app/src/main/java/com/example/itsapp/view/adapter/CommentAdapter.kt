package com.example.itsapp.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.itsapp.R
import com.example.itsapp.model.vo.Comment
import com.example.itsapp.model.vo.Review


class CommentAdapter(var commentList:ArrayList<Comment>) : RecyclerView.Adapter<CommentAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.comment_item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: CommentAdapter.ViewHolder, position: Int) {
        holder.writer.text = commentList.get(position).writer
        holder.comment.text = commentList.get(position).comment
        holder.writeTime.text = commentList.get(position).writeTime
    }

    override fun getItemCount(): Int {
        return commentList.size
    }

    fun updateItem(item: List<Comment>){
        commentList = item as ArrayList<Comment>
        notifyDataSetChanged()
    }
    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val writer = itemView.findViewById<TextView>(R.id.comment_writer)
        val comment = itemView.findViewById<TextView>(R.id.comment_content)
        val writeTime = itemView.findViewById<TextView>(R.id.comment_write_time)
    }
}