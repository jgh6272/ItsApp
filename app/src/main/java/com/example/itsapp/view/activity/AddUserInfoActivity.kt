package com.example.itsapp.view.activity

import android.app.ActivityOptions
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.itsapp.R
import com.example.itsapp.viewmodel.JoinViewModel
import com.google.android.material.snackbar.Snackbar
import com.kakao.sdk.user.UserApiClient
import kotlinx.android.synthetic.main.activity_add_user_info.*
import com.kakao.sdk.user.UserApiClient.Companion as User

class AddUserInfoActivity : AppCompatActivity() {

    private var checkNick = false
    private val viewModel:JoinViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_user_info)

        eventBtn()
        liveData()
    }
    private fun eventBtn(){
        kakao_nick_check_btn.setOnClickListener {
            val nickname = kakao_nick_et.text.toString().trim()
            viewModel.checkNick(nickname)
        }

        kakaoId_update_btn.setOnClickListener {
            val id = intent.getStringExtra("userId")
            val nickname = kakao_nick_et.text.toString().trim()
            if(id!=null&&checkNick){
                Log.d("TAG", "eventBtn: "+nickname)
                viewModel.kakaoUserInfo(id,nickname)
            }else {
                Snackbar.make(add_user_info_activity,"에러",Snackbar.LENGTH_SHORT).show()
            }
        }
    }
    private fun liveData(){
        viewModel.checkNicknameLiveData.observe(this, Observer {code ->
            if(code.equals("200")){
                kakao_nickname_input_layout.helperText = "닉네임 사용 가능"
                checkNick = true
            }else if(code.equals("204")){
                kakao_nickname_input_layout.error = "이미 사용중인 닉네임입니다."
                checkNick = false
            }else{
                Snackbar.make(add_user_info_activity,"에러",Snackbar.LENGTH_SHORT).show()
                checkNick = false
            }
        })
        viewModel.kakaoUserInfoLD.observe(this, Observer { code ->
            if(code.equals("200")){
                Snackbar.make(add_user_info_activity,"닉네임 설정 성공",Snackbar.LENGTH_LONG).show()
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle())
                finish()
            }else {
                Snackbar.make(add_user_info_activity,"회원가입 실패",Snackbar.LENGTH_SHORT).show()
                kakao_nick_et.text?.clear()
                kakao_nick_et.requestFocus()
            }
        })
    }
}