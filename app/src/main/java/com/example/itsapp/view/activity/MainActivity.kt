package com.example.itsapp.view.activity

import android.app.ActivityOptions
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.itsapp.R
import com.example.itsapp.util.SharedPreference
import com.example.itsapp.viewmodel.JoinViewModel
import com.example.itsapp.viewmodel.LoginViewModel
import com.google.android.material.snackbar.Snackbar
import com.kakao.sdk.auth.LoginClient
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.user.UserApiClient
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.login_btn

class MainActivity : AppCompatActivity() {
    private var images = intArrayOf(
        R.drawable.review,
        R.drawable.comment,
        R.drawable.graph
    )
    private val viewModel: JoinViewModel by viewModels()
    companion object{
        lateinit var prefs: SharedPreference
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        prefs = SharedPreference(applicationContext)
        /*이미지 자동 슬라이드*/
        viewSlide()
        /*버튼 이벤트*/
        eventBtn()
        /*카카오 자동 로그인*/
        kakaoAutoLogin()
        liveData()
    }
    private fun eventBtn(){
        /*카카오톡 로그인 버튼*/
        kakao_signin_btn.setOnClickListener {
            kakaoLogin()
        }
        login_btn.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent,ActivityOptions.makeSceneTransitionAnimation(this).toBundle())
        }
        /*회원가입 버튼*/
        join_btn.setOnClickListener{
            val intent = Intent(this, JoinActivity::class.java)
            startActivity(intent,ActivityOptions.makeSceneTransitionAnimation(this).toBundle())
        }
    }
    /*카카오 자동 로그인*/
    fun kakaoAutoLogin(){
        UserApiClient.instance.me { user, error ->
            if(error !=null){
                Log.e("TAG", "사용자 요청 실패",error )
            }else if(user !=null){
                if(user.kakaoAccount?.email != null){
                    val intent = Intent(this, LoadingActivity::class.java)
                    startActivity(intent,ActivityOptions.makeSceneTransitionAnimation(this).toBundle())
                }
            }
        }
    }
    fun kakaoLogin(){
        val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
            if (error != null) {
                Log.e("MainActivity 카카오 로그인 : ", "로그인 실패", error)
            } else if (token != null) {
                UserApiClient.instance.me { user, error ->
                    if (error != null) {
                        Log.e("TAG", "사용자 정보 요청 실패", error)
                    }
                    else if (user != null) {
                        Log.i("MainActivity 카카오 로그인 : ", "로그인 성공 ${token.accessToken}")
                        val userId = user.kakaoAccount?.email
                        val userName = user.kakaoAccount?.profile?.nickname
                        prefs.putLoginMethod("카카오")
                        viewModel.kakaoLogin(userId!!, userName!!)
                    }
                }
            }
        }
        if(LoginClient.instance.isKakaoTalkLoginAvailable(this)){
            LoginClient.instance.loginWithKakaoAccount(this, callback = callback)
        } else {
            LoginClient.instance.loginWithKakaoAccount(this, callback = callback)
        }
    }
    fun liveData(){
        viewModel.kakaoLoginLiveData.observe(this, Observer { code ->
            if(code.equals("200")){
                Snackbar.make(main_activity,"로그인 성공",Snackbar.LENGTH_SHORT).show()
                val intent = Intent(this, LoadingActivity::class.java)
                intent.putExtra("loginMethod","kakao")
                startActivity(intent,ActivityOptions.makeSceneTransitionAnimation(this).toBundle())
            }else if(code.equals("204")){
                Snackbar.make(main_activity,"카카오 계정과 동일한 아이디가 존재합니다.",Snackbar.LENGTH_SHORT).show()
            }else {
                Snackbar.make(main_activity,"에러", Snackbar.LENGTH_SHORT).show()
            }
        })
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