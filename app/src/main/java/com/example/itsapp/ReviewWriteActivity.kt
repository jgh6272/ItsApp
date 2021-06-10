package com.example.itsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.itsapp.viewmodel.DeviceViewModel
import kotlinx.android.synthetic.main.activity_review_write.*

class ReviewWriteActivity : AppCompatActivity() {

    private val viewModel: DeviceViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_review_write)

        back_btn.setOnClickListener {
            finish()
        }

        rating_bar.setOnRatingBarChangeListener { ratingBar, rating, fromUser ->
            review_point.text = ratingBar.rating.toString()
        }
        val intent = intent
        val deviceName = intent.getStringExtra("deviceName")
        Log.i("getString", deviceName.toString())

        viewModel.getDeviceInfo(deviceName!!)
        viewModel.deviceInfoLiveData.observe(this, Observer { deviceInfo ->
            if(deviceInfo.code.equals("200")){
                device_brand.text = deviceInfo.jsonArray[0].deviceBrand
                device_name.text = deviceInfo.jsonArray[0].deviceName
            }
        })
    }
}