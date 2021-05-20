package com.example.itsapp

import android.app.ActivityOptions
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.itsapp.view.HomeActivity

class LoadingActivity : AppCompatActivity() {
    private val SPLASH_TIME_OUT:Long = 3000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loading)
    }
    private fun startLoading(){
        val handler:Handler = Handler(Looper.getMainLooper())
        handler.postDelayed({
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle())
            finish()
        },SPLASH_TIME_OUT)
    }
}