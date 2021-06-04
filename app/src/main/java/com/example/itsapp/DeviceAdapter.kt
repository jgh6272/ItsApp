package com.example.itsapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.itsapp.model.vo.Device

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
        holder.deviceName.text = deviceList.get(position).deviceName
        holder.reviewPoint.text = deviceList.get(position).reviewPoint.toString()
        holder.reviewCount.text = deviceList.get(position).reviewCount.toString()

        holder.itemView.setOnClickListener{
            itemClickListener.onClick(it, position)
        }
    }

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val deviceName = itemView.findViewById<TextView>(R.id.device_name)
        val reviewPoint = itemView.findViewById<TextView>(R.id.review_point)
        val reviewCount = itemView.findViewById<TextView>(R.id.review_count)
    }
    interface OnItemClickListener{
        fun onClick(v: View, position: Int)
    }
    fun setItemClickListener(onItemClickListener: OnItemClickListener){
        this.itemClickListener = onItemClickListener
    }
}