package com.example.itsapp.view

import android.app.ActivityOptions
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.widget.Toast
import com.example.itsapp.R
import com.example.itsapp.viewmodel.JoinViewModel
import androidx.activity.viewModels
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_join.*
import java.util.regex.Pattern

class JoinActivity : AppCompatActivity() {

    private var checkId = false
    private var checkPw = false
    private var checkValidPw = false
    private val viewModel: JoinViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join)

        checkId()
        backBtn()
        joinBtn()
        checkPw()
        liveData()

    }
    /*뒤로가기 버튼*/
    private fun backBtn(){
        back_btn.setOnClickListener {
            finish()
        }
    }
    private fun checkPw(){
        join_password_edt.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {

            }
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                /*비밀번호 유효성 검사 (영문,숫자,특수문자)*/
                val rule = "^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[\$@\$!%*#?&]).{8,20}.\$"
                val password = join_password_edt.text.toString().trim()
                checkPw = if(Pattern.matches(rule,password)) {
                    password_check_iv.setImageResource(R.drawable.join_password_right)
                    true
                }else {
                    password_check_iv.setImageResource(R.drawable.join_password_wrong)
                    false
                }
            }
        })
        join_password_check_edt.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {

            }
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val password = join_password_edt.text.toString().trim()
                val checkPassword = join_password_check_edt.text.toString().trim()
                checkValidPw = if(checkPassword.equals(password)) {
                    password_valid_check_iv.setImageResource(R.drawable.join_password_right)
                    true
                }else{
                    password_valid_check_iv.setImageResource(R.drawable.join_password_wrong)
                    false
                }
            }
        })
    }
    /*아이디 중복 검사*/
    private fun checkId(){
        check_id.setOnClickListener {
            /*간단한 이메일 유효성 검사*/
            val pattern = Patterns.EMAIL_ADDRESS
            val userId = join_id_edt.text.toString().trim()
            if(!userId.equals("")&&pattern.matcher(userId).matches()){
                viewModel.checkId(userId)
            }else {
                Snackbar.make(join_activity,"적합한 아이디(이메일)을 입력해 주세요.",Snackbar.LENGTH_LONG).show()
            }
        }
    }
    /*JoinBtn*/
    private fun joinBtn(){
        val userId = join_id_edt.text.toString().trim()
        val password = join_password_edt.text.toString().trim()
        val checkPassword = join_password_check_edt.text.toString().trim()
        val userName = join_name_edt.text.toString().trim()
        val userNickName = join_nick_name_edt.text.toString().trim()
        val joinMethod = "일반"
        join_btn.setOnClickListener {
            if (userId == "" && password == "" && userName == "" && userNickName == "")
            viewModel.join(userId,password,userName,userNickName,joinMethod)
            Toast.makeText(this,"회원가입 버튼 클릭", Toast.LENGTH_SHORT).show()
        }
    }
    /*LiveData*/
    private fun liveData(){
        /*아이디 중복 검사 LIVEDATA*/
        viewModel.checkIdLiveData.observe(this, Observer { code->
            if(code == "200"){
                Snackbar.make(join_activity,"아이디(이메일) 사용 가능",Snackbar.LENGTH_LONG).show()
                checkId=true
            }else {
                Snackbar.make(join_activity,"이미 사용중인 아이디(이메일)입니다.",Snackbar.LENGTH_LONG).show()
                checkId=false
            }
        })
        /*회원가입 LIVEDATA*/
        viewModel.joinLiveData.observe(this, Observer {code ->
            if(code == "200"){
                Snackbar.make(join_activity,"회원가입 성공",Snackbar.LENGTH_LONG).show()
                val intent = Intent(this,HomeActivity::class.java)
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle())
                finish()
            }else{
                Snackbar.make(join_activity,"회원가입 실패",Snackbar.LENGTH_LONG).show()
                join_id_edt.text.clear()
                join_id_edt.requestFocus()
                join_password_edt.text.clear()
                join_password_check_edt.text.clear()
                join_name_edt.text.clear()
                join_nick_name_edt.text.clear()
            }
        })
    }
}
