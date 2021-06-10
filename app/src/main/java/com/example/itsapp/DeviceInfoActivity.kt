package com.example.itsapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.RatingBar
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.itsapp.model.vo.DeviceInfo
import com.example.itsapp.viewmodel.DeviceViewModel
import com.example.itsapp.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.activity_device_info.*

class DeviceInfoActivity : AppCompatActivity() {

    private val viewModel: DeviceViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_device_info)

        back_btn.setOnClickListener {
            finish()
        }
//        go_to_review_write_activity.setOnClickListener {
//            val intent = Intent(this,ReviewWriteActivity::class.java)
//            startActivity(intent)
//        }

        // 디바이스를 선택한 프래그먼트로 부터 deviceName을 넘겨 받아
        // deviceName에 저장한다.
        val intent = intent
        val deviceName = intent.getStringExtra("deviceName")
        Log.i("getString", deviceName.toString())

        viewModel.getDeviceInfo(deviceName!!)
        viewModel.deviceInfoLiveData.observe(this, Observer { deviceInfo ->
            if(deviceInfo.code.equals("200")){
                device_brand.text = deviceInfo.jsonArray[0].deviceBrand
                device_name.text = deviceInfo.jsonArray[0].deviceName
                device_point_avg.text = deviceInfo.jsonArray[0].reviewPoint.toString()
                device_point_avg2.text = deviceInfo.jsonArray[0].reviewPoint.toString()
                device_price.text = deviceInfo.jsonArray[0].devicePrice
                review_count.text = deviceInfo.jsonArray[0].reviewCount.toString()
                rating_bar.rating = deviceInfo.jsonArray[0].reviewPoint.toFloat()
                rating_bar2.rating = deviceInfo.jsonArray[0].reviewPoint.toFloat()
            }
        })
        viewModel.deviceInfoLiveData.observe(this, Observer { deviceInfo ->
            if(deviceInfo.code.equals("200")){
                go_to_review_write_activity.setOnClickListener {
                    val deviceName = deviceInfo.jsonArray[0].deviceName
                    val intent = Intent(this, ReviewWriteActivity::class.java)
                    intent.putExtra("deviceName",deviceName)
                    startActivity(intent)
                }
            }
        })
    }
}