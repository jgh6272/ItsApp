package com.example.itsapp.view

import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.itsapp.R
import com.example.itsapp.viewmodel.LoginViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private var password:String = ""
    private val viewModel:LoginViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        uiFunc()
    }
    /*비밀번호 찾기 밑줄 ui 코드*/
    private fun uiFunc(){
        forgot_password_tv.paintFlags = forgot_password_tv.paintFlags or Paint.UNDERLINE_TEXT_FLAG
    }
    /*로그인 코드*/
    private fun login(){
        login_btn.setOnClickListener {
            val userId = login_id_et.text.toString().trim()
            val userPw = login_pw_et.text.toString().trim()
            if(userId.equals("")||userPw.equals(""))
            {
                Snackbar.make(login_layout,"로그인 정보를 입력하세요.",Snackbar.LENGTH_SHORT).show()
            }else{
                viewModel.login(userId)
            }
        }
    }
    private fun liveData(){
        viewModel.loginLiveData.observe(this, Observer {user->
            if(user.code.equals("200")){
                password = user.jsonArray[0].password
            }
        })
    }
}