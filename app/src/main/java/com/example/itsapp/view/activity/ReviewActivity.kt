package com.example.itsapp.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.itsapp.R
import com.example.itsapp.model.vo.Review
import com.example.itsapp.view.adapter.ReviewAdapter
import com.example.itsapp.viewmodel.ReviewViewModel
import kotlinx.android.synthetic.main.activity_device_info.*

class ReviewActivity : AppCompatActivity() {

    private val reviewViewModel: ReviewViewModel by viewModels()
    val reviewList = arrayListOf<Review>()
    val reviewAdapter = ReviewAdapter(reviewList)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_review)

        back_btn.setOnClickListener {
            finish()
        }

        val intent = intent
        val deviceName = intent.getStringExtra("deviceName")

        rv_review.layoutManager = LinearLayoutManager(this)
        rv_review.adapter = reviewAdapter
        rv_review.addItemDecoration(DividerItemDecoration(this, 1));

        reviewViewModel.getReviewAll(deviceName!!)
        reviewViewModel.reviewLiveData.observe(this, Observer { reviewInfo ->
            if(reviewInfo.code.equals("200")) {
                Log.i("getReview", reviewInfo.jsonArray.toString())
                reviewAdapter.updateItem(reviewInfo.jsonArray)
            }
        })
    }
}