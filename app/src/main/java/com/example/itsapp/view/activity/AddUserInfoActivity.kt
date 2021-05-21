package com.example.itsapp.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.itsapp.R
import com.example.itsapp.viewmodel.JoinViewModel
import kotlinx.android.synthetic.main.activity_add_user_info.*

class AddUserInfoActivity : AppCompatActivity() {

    private val viewModel:JoinViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_user_info)

        val id = intent.getStringExtra("email")
        val name = intent.getStringExtra("name")
        val nickname = kakao_nick_et.text.toString().trim()

        kakao_nick_check_btn.setOnClickListener {
            viewModel.checkNick(nickname)
        }

        kakao_join_btn.setOnClickListener {
            if(id!=null&&name!=null){
                viewModel.kakaoJoin(id,name,nickname)
            }
        }
    }
}