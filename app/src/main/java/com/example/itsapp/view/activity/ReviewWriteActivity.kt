package com.example.itsapp.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.itsapp.R
import com.example.itsapp.viewmodel.DeviceViewModel
import com.example.itsapp.viewmodel.HomeViewModel
import com.example.itsapp.viewmodel.ReviewViewModel
import kotlinx.android.synthetic.main.activity_review_write.*

class ReviewWriteActivity : AppCompatActivity() {
    private val deviceViewModel: DeviceViewModel by viewModels()
    private val reviewViewModel: ReviewViewModel by viewModels()
    private val homeViewmodel: HomeViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_review_write)

        back_btn.setOnClickListener {
            finish()
        }

        rating_bar.setOnRatingBarChangeListener { ratingBar, rating, fromUser ->
            review_point.text = ratingBar.rating.toString()
            //Toast.makeText(this,ratingBar.rating.toString(),Toast.LENGTH_SHORT).show()
        }

        val intent = intent
        val deviceName = intent.getStringExtra("deviceName")
        Log.i("getString", deviceName.toString())

        deviceViewModel.getDeviceInfo(deviceName!!)
        deviceViewModel.deviceInfoLiveData.observe(this, Observer { deviceInfo ->
            if(deviceInfo.code.equals("200")){
                device_brand.text = deviceInfo.jsonArray[0].deviceBrand
                device_name.text = deviceInfo.jsonArray[0].deviceName
            }
        })

        write_btn.setOnClickListener {
            val deviceName = deviceName
            val userId = "alsrbs12304@naver.com"
            val reviewPoint:Int = rating_bar.rating.toInt()
            val contentPros = content_pros_edt.text.toString()
            val contentCons = content_cons_edt.text.toString()
            if(reviewPoint == 0){
                Toast.makeText(this,"리뷰 점수 선택해주세요.",Toast.LENGTH_SHORT).show()
            }
            else if(contentPros.length < 20 && contentCons.length < 20){
                Toast.makeText(this,"리뷰 내용을 20자 이상 적어주세요.", Toast.LENGTH_SHORT).show()
            }else{
                reviewViewModel.writeReview(deviceName,userId,reviewPoint,contentPros,contentCons)
            }
        }
    }
}