package com.example.itsapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.FrameLayout
import android.widget.ImageView
import com.kakao.sdk.common.util.Utility
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var images = intArrayOf(
        R.drawable.review,
        R.drawable.comment,
        R.drawable.graph
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val keyHash = Utility.getKeyHash(this)
        Log.d("HashKey",keyHash)

//        image_slide.inAnimation = AnimationUtils.loadAnimation(this,R.anim.sl)
        for (image in images){
            val imageView = ImageView(this)
            val layoutParams = FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT)
            layoutParams.setMargins(30,30,30,30)
            layoutParams.gravity = Gravity.CENTER
            imageView.layoutParams = layoutParams
            imageView.setImageResource(image)
            image_slide.addView(imageView)
        }
        image_slide.flipInterval = 3000
        image_slide.startFlipping()

        join_btn.setOnClickListener{
            val intent = Intent(this, JoinActivity::class.java)
            startActivity(intent)
        }

        login_btn.setOnClickListener {
            val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
        }
    }
}