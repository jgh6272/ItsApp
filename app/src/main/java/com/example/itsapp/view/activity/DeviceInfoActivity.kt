package com.example.itsapp.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.itsapp.R
import com.example.itsapp.model.vo.Review
import com.example.itsapp.view.adapter.ReviewAdapter
import com.example.itsapp.viewmodel.DeviceViewModel
import com.example.itsapp.viewmodel.ReviewViewModel
import kotlinx.android.synthetic.main.activity_device_info.*
import kotlinx.android.synthetic.main.review_item.*

class DeviceInfoActivity : AppCompatActivity() {

    private val deviewViewModel: DeviceViewModel by viewModels()
    private val reviewViewModel: ReviewViewModel by viewModels()
    val reviewList = arrayListOf<Review>()
    val reviewAdapter = ReviewAdapter(reviewList)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_device_info)

        back_btn.setOnClickListener {
            finish()
        }


        // 디바이스를 선택한 프래그먼트로 부터 deviceName을 넘겨 받아
        // deviceName에 저장한다.
        val intent = intent
        val deviceName = intent.getStringExtra("deviceName")
        Log.i("getString", deviceName.toString())

        deviewViewModel.getDeviceInfo(deviceName!!)
        deviewViewModel.deviceInfoLiveData.observe(this, Observer { deviceInfo ->
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
        deviewViewModel.deviceInfoLiveData.observe(this, Observer { deviceInfo ->
            if(deviceInfo.code.equals("200")){
                go_to_review_write_activity.setOnClickListener {
                    val deviceName = deviceInfo.jsonArray[0].deviceName
                    val intent = Intent(this, ReviewWriteActivity::class.java)
                    intent.putExtra("deviceName",deviceName)
                    startActivity(intent)
                }
            }
        })

        go_to_review_all.setOnClickListener {
            val intent = Intent(this,ReviewActivity::class.java)
            intent.putExtra("deviceName",deviceName)
            startActivity(intent)
        }

        rv_review.layoutManager = LinearLayoutManager(this)
        rv_review.adapter = reviewAdapter
        rv_review.addItemDecoration(DividerItemDecoration(this, 1));

        // DeviewInfoActivity에서 보여지는 3개의 리뷰는
        // 리뷰의 좋아요 수가 제일 높은 상위 3개만 보여진다.
        reviewViewModel.getReviewThird(deviceName)
        reviewViewModel.reviewLiveData.observe(this, Observer { reviewInfo ->
            if(reviewInfo.code.equals("200")){
                Log.i("getReview", reviewInfo.jsonArray.toString())
                reviewAdapter.updateItem(reviewInfo.jsonArray)
            }
        })

        // 해당 디바이스에 대한 리뷰 점수 별 리뷰 개수를 불러온다.
        reviewViewModel.getReviewPointCount(deviceName)
        reviewViewModel.reviewPointCountLiveData.observe(this, Observer { deviceInfo ->
            if(deviceInfo.code.equals("200")){
                review_count_5_point_text.text = deviceInfo.jsonArray[0].reviewPoint5Count.toString()
                review_count_4_point_text.text = deviceInfo.jsonArray[0].reviewPoint4Count.toString()
                review_count_3_point_text.text = deviceInfo.jsonArray[0].reviewPoint3Count.toString()
                review_count_2_point_text.text = deviceInfo.jsonArray[0].reviewPoint2Count.toString()
                review_count_1_point_text.text = deviceInfo.jsonArray[0].reviewPoint1Count.toString()
            }
        })
    }
}