package com.example.itsapp.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.itsapp.R
import com.example.itsapp.model.vo.Device

class CategoryAdapter(private val items:List<Device>) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>(),View.OnClickListener{
    class ViewHolder(view:View) :RecyclerView.ViewHolder(view) {
        val brand : TextView
        init {
            brand = view.findViewById(R.id.brand_tv)
        }
        fun setText(items:Device){
            brand.text = items.deviceBrand
        }

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_part,parent,false)
        return ViewHolder(view)
    }
    override fun getItemCount() = items.size
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var items:Device = items.get(position)
        holder.setText(items)
    }

    override fun onClick(p0: View?) {
        TODO("Not yet implemented")
    }
}