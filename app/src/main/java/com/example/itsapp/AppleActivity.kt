package com.example.itsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_apple.*

class AppleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_apple)

        back_btn.setOnClickListener {
            finish()
        }
//        val deviceList = arrayListOf(
//            Device(R.drawable.macbook,"Mac Book Pro 16인치"),
//            Device(R.drawable.macbook,"Mac Book Pro 16인치"),
//            Device(R.drawable.macbook,"Mac Book Pro 16인치"),
//            Device(R.drawable.macbook,"Mac Book Pro 16인치"),
//            Device(R.drawable.macbook,"Mac Book Pro 16인치"),
//            Device(R.drawable.macbook,"Mac Book Pro 16인치"),
//            Device(R.drawable.macbook,"Mac Book Pro 16인치"),
//            Device(R.drawable.macbook,"Mac Book Pro 16인치"),
//            Device(R.drawable.macbook,"Mac Book Pro 16인치"),
//            Device(R.drawable.macbook,"Mac Book Pro 16인치")
//        )
//        rv_device.layoutManager = GridLayoutManager(this,2)
//        rv_device.adapter = DeviceAdapter(deviceList)
    }
}