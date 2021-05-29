package com.example.itsapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DeviceAdapter(val deviceList:ArrayList<Device>) : RecyclerView.Adapter<DeviceAdapter.ItemViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeviceAdapter.ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.device_item,parent,false)
        return ItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return deviceList.size
    }

    override fun onBindViewHolder(holder: DeviceAdapter.ItemViewHolder, position: Int) {
        holder.deviceImg.setImageResource(deviceList.get(position).deviceImg)
        holder.name.text = deviceList.get(position).name
    }

    class ItemViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val deviceImg = itemView.findViewById<ImageView>(R.id.device_img)
        val name = itemView.findViewById<TextView>(R.id.device_name)
    }
    interface OnItemClickListener{
        fun onItemClick(v:View, data: Device, pos : Int)
    }
    private var listener : OnItemClickListener? = null
    fun setOnItemClickListener(listener : OnItemClickListener) {
        this.listener = listener
    }
}