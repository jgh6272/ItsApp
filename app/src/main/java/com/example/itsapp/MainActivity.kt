package com.example.itsapp

import android.app.ActivityOptions
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.Toast
import com.kakao.sdk.auth.LoginClient
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.AuthErrorCause
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
        /*이미지 자동 슬라이드*/
        viewSlide();
        /*카카오톡 로그인 버튼*/
        kakao_signin_btn.setOnClickListener {
            kakaoLogin()
        }
        /*회원가입 버튼*/
        join_btn.setOnClickListener{
            val intent = Intent(this, JoinActivity::class.java)
            startActivity(intent)
        }
    }
    fun kakaoLogin(){
        val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
            if (error != null) {
                Log.e("MainActivity 카카오 로그인 : ", "로그인 실패", error)
            } else if (token != null) {
                Log.i("MainActivity 카카오 로그인 : ", "로그인 성공 ${token.accessToken}")
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent,ActivityOptions.makeSceneTransitionAnimation(this).toBundle())
            }
        }
        if(LoginClient.instance.isKakaoTalkLoginAvailable(this)){
            LoginClient.instance.loginWithKakaoAccount(this, callback = callback)
        } else {
            LoginClient.instance.loginWithKakaoAccount(this, callback = callback)
        }
    }
    fun viewSlide(){
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
    }
}