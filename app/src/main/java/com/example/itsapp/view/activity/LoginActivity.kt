package com.example.itsapp.view.activity

import android.app.ActivityOptions
import android.app.AlertDialog
import android.content.Intent
import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.itsapp.R
import com.example.itsapp.viewmodel.LoginViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.back_btn
import org.mindrot.jbcrypt.BCrypt

class LoginActivity : AppCompatActivity() {


    private val viewModel:LoginViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        uiFunc()
        buttonEvent()
        liveData()
    }
    /*비밀번호 찾기 밑줄 ui 코드*/
    private fun uiFunc(){
        forgot_password_tv.paintFlags = forgot_password_tv.paintFlags or Paint.UNDERLINE_TEXT_FLAG
    }
    /*로그인 코드*/
    private fun buttonEvent(){
        /*뒤로가기 버튼*/
        back_btn.setOnClickListener {
            finish()
        }
        /*로그인 버튼 */
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
        forgot_password_tv.setOnClickListener{
            val builder =AlertDialog.Builder(this)
            val dialogView = layoutInflater.inflate(R.layout.dialog_find_password,null)
            val dialogText = dialogView.findViewById<EditText>(R.id.dialog_id)
            builder.setView(dialogView)
                .setTitle("비밀번호 찾기")
                .setPositiveButton("확인"){ dialogInterface, i ->

                }
                .setNegativeButton("취소"){dialogInterface, i ->

                }
                .show()
        }
    }
    private fun liveData(){
        viewModel.loginLiveData.observe(this, Observer {user->
            val userPw = login_pw_et.text.toString().trim()
            if(user.code.equals("200")){
                val isValidPassword = BCrypt.checkpw(userPw,user.jsonArray)
                if(isValidPassword){
                    Snackbar.make(login_layout,"로그인 성공",Snackbar.LENGTH_SHORT).show()
                    val intent = Intent(this, HomeActivity::class.java)
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