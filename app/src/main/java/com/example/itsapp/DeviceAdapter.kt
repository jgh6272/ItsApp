package com.example.itsapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DeviceAdapter(val deviceList:ArrayList<Device>) : RecyclerView.Adapter<DeviceAdapter.ViewHolder>(){

    private lateinit var itemClickListener : OnItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeviceAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.device_item,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return deviceList.size
    }

    override fun onBindViewHolder(holder: DeviceAdapter.ViewHolder, position: Int) {
        holder.deviceImg.setImageResource(deviceList.get(position).deviceImg)
        holder.name.text = deviceList.get(position).name

        holder.itemView.setOnClickListener{
            itemClickListener.onClick(it, position)
        }
    }

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val deviceImg = itemView.findViewById<ImageView>(R.id.device_img)
        val name = itemView.findViewById<TextView>(R.id.device_name)
    }
    interface OnItemClickListener{
        fun onClick(v: View, position: Int)
    }
    fun setItemClickListener(onItemClickListener: OnItemClickListener){
        this.itemClickListener = onItemClickListener
    }
}