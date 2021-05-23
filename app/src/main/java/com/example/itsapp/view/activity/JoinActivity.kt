package com.example.itsapp.view.activity

import android.app.ActivityOptions
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import com.example.itsapp.R
import com.example.itsapp.viewmodel.JoinViewModel
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_join.*
import org.mindrot.jbcrypt.BCrypt
import java.util.regex.Pattern

class JoinActivity : AppCompatActivity() {

    private var checkId = false
    private var checkNick = false
    private var checkPw = false
    private var checkValidPw = false
    private var checkName = false
    private val viewModel: JoinViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join)
        liveData()
        btnEvent()
        textWarcher()
    }
    private fun btnEvent(){
        /*회원가입 버튼*/
        join_btn.setOnClickListener {
            val userId = join_id_edt.text.toString().trim()
            val password = join_password_edt.text.toString().trim()
            val encryptionPw = BCrypt.hashpw(password, BCrypt.gensalt())
            val userName = join_name_edt.text.toString().trim()
            val userNickName = join_nick_name_edt.text.toString().trim()
            val joinMethod = "일반"
            if(!checkName){
                join_name_edt.requestFocus()
            }else if(!checkNick && !checkId) {
                Snackbar.make(join_activity,"중복 검사 실시해주세요.",Snackbar.LENGTH_SHORT).show()
            }else if(!checkPw){
                Snackbar.make(join_activity,"비밀번호 조건에 맞게 작성해 주세요.",Snackbar.LENGTH_SHORT).show()
            }else if(!checkValidPw){
                Snackbar.make(join_activity,"비밀번호가 일치 하지 않습니다.",Snackbar.LENGTH_SHORT).show()
            } else{
                viewModel.join(userId,encryptionPw,userName,userNickName,joinMethod)
                Snackbar.make(join_activity,"회원가입 성공.",Snackbar.LENGTH_SHORT).show()
            }
        }
        /*뒤로가기 버튼*/
        back_btn.setOnClickListener {
            finish()
        }
        /*아이디 중복 검사*/
        check_id.setOnClickListener {
            val userId = join_id_edt.text.toString().trim()
            /*간단한 이메일 유효성 검사*/
            val pattern = Patterns.EMAIL_ADDRESS
            checkId = if(!userId.equals("")&&pattern.matcher(userId).matches()){
                viewModel.checkId(userId)
                true
            }else {
                id_input_layout.error="적합한 아이디(이메일)을 입력해 주세요."
                false
            }
        }
        /*닉네임 중복 검사*/
        check_nick.setOnClickListener {
            val userNickName = join_nick_name_edt.text.toString().trim()
            if(!userNickName.equals("")){
                viewModel.checkNick(userNickName)
            }else{
                Snackbar.make(join_activity,"닉네임을 입력해 주세요.",Snackbar.LENGTH_SHORT).show()
            }
        }
    }
    private fun textWarcher(){
        join_id_edt.addTextChangedListener(object :TextWatcher{
            override fun afterTextChanged(p0: Editable?) {
                if(join_id_edt.text!!.isEmpty()){
                    id_input_layout.error="아이디(이메일)을 입력해주세요."
                }else {
                    id_input_layout.error=null
                }
            }
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        })
        join_password_edt.addTextChangedListener(object :TextWatcher{
            override fun afterTextChanged(p0: Editable?) {
                val rule = "^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[\$@\$!%*#?&]).{7,19}.\$"
                val password = join_password_edt.text.toString().trim()
                if(join_password_edt.text!!.isEmpty()){
                    pw_input_layout.error="비밀번호를 입력하세요."
                    checkPw=false
                }else if(Pattern.matches(rule,password)) {
                    pw_input_layout.error= null
                    checkPw = true
                }else{
                    pw_input_layout.error = "영문/숫자/특수문자(~!@#\$)로 8~20자로 입력하세요."
                    checkPw = false
                }
            }
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        })
        join_password_check_edt.addTextChangedListener(object :TextWatcher{
            override fun afterTextChanged(p0: Editable?) {
                val password = join_password_edt.text.toString().trim()
                if(join_password_check_edt.text!!.isEmpty()){
                    valid_id_layout.error="비밀번호를 입력하세요."
                    checkValidPw = false
                }else if(join_password_check_edt.text.toString().trim().equals(password)) {
                    valid_id_layout.error=null
                    checkValidPw =  true
                }else{
                    valid_id_layout.error="비밀번호가 일치하지 않습니다."
                    checkValidPw = false
                }
            }
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        })
        join_name_edt.addTextChangedListener(object :TextWatcher{
            override fun afterTextChanged(p0: Editable?) {
                if(join_name_edt.text!!.isEmpty()){
                    name_input_layout.error = "이름을 입력해주세요."
                    checkName = false
                }else{
                    name_input_layout.error = null
                    checkName = true
                }
            }
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        })
        join_nick_name_edt.addTextChangedListener(object :TextWatcher{
            override fun afterTextChanged(p0: Editable?) {
                if(join_nick_name_edt.text!!.isEmpty()){
                    nickname_input_layout.error = "닉네임을 입력해주세요."
                    checkNick = false
                }else{
                    nickname_input_layout.error = null
                    checkNick = false
                }
            }
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        })
    }
    /*LiveData*/
    private fun liveData(){
        /*아이디 중복 검사 LIVEDATA*/
        viewModel.checkIdLiveData.observe(this, Observer { code->
            checkId = if(code.equals("200")){
                id_input_layout.helperText="아이디(이메일) 사용가능"
                true
            }else if(code.equals("204")){
                id_input_layout.error="이미 사용중인 아이디입니다."
                false
            }else{
                Snackbar.make(join_activity,"에러",Snackbar.LENGTH_SHORT).show()
                false
            }
        })
        /*닉네임 중복 검사 LIVEDATA*/
        viewModel.checkNicknameLiveData.observe(this, Observer { code->
            checkNick = if(code.equals("200")){
                nickname_input_layout.helperText = "닉네임 사용 가능"
                true
            }else if(code.equals("204")){
                nickname_input_layout.error = "이미 사용중인 닉네임입니다."
                false
            }else{
                Snackbar.make(join_activity,"에러",Snackbar.LENGTH_SHORT).show()
                false
            }
        })
        /*회원가입 LIVEDATA*/
        viewModel.joinLiveData.observe(this, Observer {code ->
            if(code.equals("200")){
                Snackbar.make(join_activity,"회원가입 성공",Snackbar.LENGTH_SHORT).show()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle())
                finish()
            }else{
                Snackbar.make(join_activity,"회원가입 실패",Snackbar.LENGTH_SHORT).show()
                join_id_edt.text?.clear()
                join_id_edt.requestFocus()
                join_password_edt.text?.clear()
                join_password_check_edt.text?.clear()
                join_name_edt.text?.clear()
                join_nick_name_edt.text?.clear()
            }
        })
    }
}
