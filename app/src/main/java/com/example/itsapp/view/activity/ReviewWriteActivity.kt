package com.example.itsapp.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.example.itsapp.R
import com.example.itsapp.viewmodel.DeviceViewModel
import com.example.itsapp.viewmodel.HomeViewModel
import com.example.itsapp.viewmodel.ReviewViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_review_write.*

class ReviewWriteActivity : AppCompatActivity() {
    private val deviceViewModel: DeviceViewModel by viewModels()
    private val reviewViewModel: ReviewViewModel by viewModels()
    private val homeViewModel : HomeViewModel by viewModels()

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

        deviceViewModel.getDeviceInfo(deviceName!!)
        deviceViewModel.deviceInfoLiveData.observe(this, Observer { deviceInfo ->
            if(deviceInfo.code.equals("200")){
                device_brand.text = deviceInfo.jsonArray[0].deviceBrand
                device_name.text = deviceInfo.jsonArray[0].deviceName
            }
        })

        content_pros_edt.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                content_pros_edt_length.text = "0"
                if(content_pros_edt.length() < 20)
                    content_pros_edt_length.setTextColor(ContextCompat.getColor(applicationContext,R.color.red700))
                else
                    content_pros_edt_length.setTextColor(ContextCompat.getColor(applicationContext,R.color.blue400))
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                var input = content_pros_edt.text.toString()
                content_pros_edt_length.text = input.length.toString()
            }

            override fun afterTextChanged(s: Editable?) {
                var input = content_pros_edt.text.toString()
                content_pros_edt_length.text = input.length.toString()
            }
        })
        content_cons_edt.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                content_cons_edt_length.text = "0"

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                var input = content_cons_edt.text.toString()
                content_cons_edt_length.text = input.length.toString()
                if(content_cons_edt.length() < 20)
                    content_cons_edt_length.setTextColor(ContextCompat.getColor(applicationContext,R.color.red700))
                else
                    content_cons_edt_length.setTextColor(ContextCompat.getColor(applicationContext,R.color.blue400))
            }

            override fun afterTextChanged(s: Editable?) {
                var input = content_cons_edt.text.toString()
                content_cons_edt_length.text = input.length.toString()
            }

        })

        write_btn.setOnClickListener {
            val deviceName = deviceName
            // 로그인된 회원의 아이디를 받아와야댐
            val userId = homeViewModel.getLoginSession()
            val reviewPoint:Int = rating_bar.rating.toInt()
            val contentPros = content_pros_edt.text.toString()
            val contentCons = content_cons_edt.text.toString()
            if(reviewPoint == 0){
                Snackbar.make(review_write_layout,"리뷰 점수를 선택해주세요.",Snackbar.LENGTH_SHORT).show()
            }
            else if(contentPros.length < 20){
                Snackbar.make(review_write_layout,"좋았던 점에 대한 리뷰 내용을 20자 이상 적어주세요.",Snackbar.LENGTH_SHORT).show()
            }else if(contentCons.length < 20){
                Snackbar.make(review_write_layout,"아쉬운 점에 대한 리뷰 내용을 20자 이상 적어주세요.",Snackbar.LENGTH_SHORT).show()
            }else{
                reviewViewModel.writeReview(deviceName,userId,reviewPoint,contentPros,contentCons)
            }
        }
        reviewViewModel.writeReviewLiveData.observe(this, Observer {
            if(it.equals("200")){
                finish()
                Toast.makeText(this,"리뷰 등록 완료!",Toast.LENGTH_SHORT).show()
                val intent = Intent(this, DeviceInfoActivity::class.java)
                intent.putExtra("deviceName",deviceName)
                startActivity(intent)
            }else if(it.equals("204")){
                Toast.makeText(this,"해당 제품에 대해 이미 리뷰를 작성했씁니다.",Toast.LENGTH_SHORT).show()
            }
        })
    }
}