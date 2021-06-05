package com.example.itsapp.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.itsapp.model.vo.Brand
import com.example.itsapp.R

class BrandAdapter(val brandList : ArrayList<Brand>) : RecyclerView.Adapter<BrandAdapter.CustomViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.device_brand_list_item,parent,false)
        return CustomViewHolder(view)
    }

    override fun getItemCount(): Int {
        return brandList.size
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.deviceImg.setImageResource(brandList.get(position).deviceImg)
        holder.brandName.text = brandList.get(position).brandName
        holder.deviceName.text = brandList.get(position).deviceName
    }

    class CustomViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val deviceImg = itemView.findViewById<ImageView>(R.id.device_img)
        val brandName = itemView.findViewById<TextView>(R.id.brand_name)
        val deviceName = itemView.findViewById<TextView>(R.id.device_name)

    }
}