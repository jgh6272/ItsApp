package com.example.itsapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.itsapp.R
import kotlinx.android.synthetic.main.activity_join.*

class JoinActivity : AppCompatActivity() {

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

            Toast.makeText(this,"회원가입 버튼 클릭",Toast.LENGTH_SHORT).show()
        }
    }
}