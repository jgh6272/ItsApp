package com.example.itsapp.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.itsapp.viewmodel.CommentViewModel
import com.example.itsapp.R
import com.example.itsapp.model.vo.Comment
import com.example.itsapp.view.adapter.CommentAdapter
import com.example.itsapp.viewmodel.HomeViewModel
import com.example.itsapp.viewmodel.ReviewViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_review_detail.*
import kotlinx.android.synthetic.main.activity_review_detail.back_btn
import kotlinx.android.synthetic.main.activity_review_detail.device_brand
import kotlinx.android.synthetic.main.activity_review_detail.device_name
import kotlinx.android.synthetic.main.activity_review_detail.review_point
import kotlinx.android.synthetic.main.activity_review_write.*

class ReviewDetailActivity : AppCompatActivity() {

    private val commentViewModel : CommentViewModel by viewModels()
    private val reviewViewModel: ReviewViewModel by viewModels()
    private val homeViewModel : HomeViewModel by viewModels()
    val commentList = arrayListOf<Comment>()
    val commentAdapter = CommentAdapter(commentList)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_review_detail)

        back_btn.setOnClickListener {
            finish()
        }

        val intent = intent
        val deviceName = intent.getStringExtra("deviceName")
        val writer = intent.getStringExtra("writer")
        Log.i("deviceName1",deviceName.toString())
        Log.i("writer1",writer.toString())

        reviewViewModel.getChoiceReview(deviceName!!, writer!!)
        reviewViewModel.reviewLiveData.observe(this, Observer { reviewInfo ->
            if(reviewInfo.code.equals("200")){
                device_brand.text = reviewInfo.jsonArray[0].deviceBrand
                device_name.text = reviewInfo.jsonArray[0].deviceName
                review_writer.text = reviewInfo.jsonArray[0].writer
                review_point.rating = reviewInfo.jsonArray[0].reviewPoint.toFloat()
                review_write_time.text = reviewInfo.jsonArray[0].writeTime
                content_pros.text = reviewInfo.jsonArray[0].contentPros
                content_cons.text = reviewInfo.jsonArray[0].contentCons
            }
        })
        rv_comment.layoutManager = LinearLayoutManager(this)
        rv_comment.adapter = commentAdapter
        rv_comment.addItemDecoration(DividerItemDecoration(this, 1));

        commentViewModel.getComment(deviceName, writer)
        commentViewModel.commentLiveData.observe(this, Observer { commentInfo ->
            if(commentInfo.code.equals("200")){
                commentAdapter.updateItem(commentInfo.jsonArray)
            }
        })

        comment_write_btn.setOnClickListener {
            val deviceName = deviceName
            val reviewWriter = writer
            val writer = homeViewModel.getLoginSession()
            val commentContent = comment_edt.text.toString()
            if(commentContent.isEmpty()){
                Snackbar.make(review_detail_layout,"댓글을 입력해주세요.", Snackbar.LENGTH_SHORT).show()
            }else{
                commentViewModel.writeComment(deviceName,reviewWriter,writer,commentContent)
            }
        }
    }
}