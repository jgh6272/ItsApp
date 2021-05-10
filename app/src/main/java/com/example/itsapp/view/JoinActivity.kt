package com.example.itsapp.view

import android.app.ActivityOptions
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.itsapp.R
import com.example.itsapp.viewmodel.JoinViewModel
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_join.*

class JoinActivity : AppCompatActivity() {

    private val viewModel: JoinViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join)

        back_btn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        join_btn.setOnClickListener {
            val userId = join_id_edt.text.toString().trim()
            val password = join_password_edt.text.toString().trim()
            val userName = join_name_edt.text.toString().trim()
            val userNickName = join_nick_name_edt.text.toString().trim()
            val joinMethod = "일반"
            viewModel.join(userName,userId,password,userNickName,joinMethod)
            Toast.makeText(this,"회원가입 버튼 클릭", Toast.LENGTH_SHORT).show()
        }
        viewModel.joinLiveData.observe(this, Observer {code ->
            if(code.equals("200")){
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