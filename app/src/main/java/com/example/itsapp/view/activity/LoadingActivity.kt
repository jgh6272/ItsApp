package com.example.itsapp.view.activity

import android.app.ActivityOptions
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.itsapp.R
import com.example.itsapp.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.activity_loading.*

class LoadingActivity : AppCompatActivity() {
    private val viewmodel:HomeViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loading)

        val animation = AnimationUtils.loadAnimation(this,R.anim.loading)
        loading_tv.animation=animation

        val userId = viewmodel.getLoginSession()
        viewmodel.userIdLiveData.observe(this, Observer { userId ->
            if(userId!=" "){
                val intent = Intent(this,
                    HomeActivity::class.java)
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle())
            }else {
                viewmodel.getLoginSession()
            }
        })
    }

}