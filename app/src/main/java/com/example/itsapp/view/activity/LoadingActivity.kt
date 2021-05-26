package com.example.itsapp.view.activity

import android.app.ActivityOptions
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.animation.AnimationUtils
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.itsapp.R
import com.example.itsapp.util.SharedPreference
import com.example.itsapp.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.activity_loading.*

class LoadingActivity : AppCompatActivity() {
    private val viewmodel:HomeViewModel by viewModels()
    companion object{
        lateinit var id:String
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loading)

        loadingEffect()
        viewmodel.getLoginSession()
        liveData()
    }
    private fun loadingEffect(){
        val animation = AnimationUtils.loadAnimation(this,R.anim.loading)
        loading_tv.animation=animation
    }
    private fun liveData(){
        viewmodel.userIdLiveData.observe(this, Observer { userId ->
            Log.d("TAG", "onCreate: $userId")
            id = userId
            if(userId!=""){
                viewmodel.seceondJoin(userId)
            }else {
                viewmodel.getLoginSession()
            }
        })
        viewmodel.secondJoinLiveData.observe(this, Observer { code ->
            if(code.equals("200")){
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle())
            }else if (code.equals("204")) {
                val intent = Intent(this, AddUserInfoActivity::class.java)
                intent.putExtra("userId",id)
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle())
            }
        })
    }

}