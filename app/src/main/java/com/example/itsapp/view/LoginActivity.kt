package com.example.itsapp.view

import android.app.ActivityOptions
import android.content.Intent
import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.itsapp.R
import com.example.itsapp.viewmodel.LoginViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_login.*
import org.mindrot.jbcrypt.BCrypt

class LoginActivity : AppCompatActivity() {

    private var password:String = ""
    private var isValidPassword = false
    private val viewModel:LoginViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        uiFunc()
        login()
        liveData()
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
            val userPw = login_pw_et.text.toString().trim()
            if(user.code.equals("200")){
                Log.d("test", "liveData: "+user.jsonArray)
                password = user.jsonArray.toString()
                isValidPassword = BCrypt.checkpw(userPw,password)
                if(isValidPassword){
                    Snackbar.make(login_layout,"로그인 성공",Snackbar.LENGTH_SHORT).show()
                    val intent = Intent(this,HomeActivity::class.java)
                    startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle())
                    finish()
                }else {
                    Snackbar.make(login_layout,"로그인 실패",Snackbar.LENGTH_SHORT).show()
                }
            }else if(user.code.equals("204")){
                Snackbar.make(login_layout,"존재하지 않는 계정입니다.",Snackbar.LENGTH_SHORT).show()
            }else {
                Snackbar.make(login_layout,"에러",Snackbar.LENGTH_SHORT).show()
            }
        })
    }
}