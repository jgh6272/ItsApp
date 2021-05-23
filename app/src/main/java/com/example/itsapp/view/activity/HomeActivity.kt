package com.example.itsapp.view.activity

import android.app.ActivityOptions
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.itsapp.view.fragment.HomeFragment
import com.example.itsapp.view.fragment.MyPageFragment
import com.example.itsapp.view.fragment.NewsFragment
import com.example.itsapp.R
import com.example.itsapp.viewmodel.LoginViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.kakao.sdk.user.UserApiClient
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    private lateinit var fragmentManager: FragmentManager
    private lateinit var  fragmentTransaction: FragmentTransaction
    private lateinit var homeFragment : HomeFragment
    private lateinit var newsFragment: NewsFragment
    private lateinit var myPageFragment: MyPageFragment
    private val viewModel:LoginViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        logout.setOnClickListener {
            UserApiClient.instance.logout { error ->
                if(error !=null){
                    Log.e("kakao", "로그아웃 실패. SDK에서 토큰 삭제됨", error )
                }else {
                    Log.i("kakao", "로그아웃 성공. SDK에서 토큰 삭제됨");
                }
            }
            viewModel.removeUserInfoPref()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle())
        }

    }

    override fun onNavigationItemSelected(p0: MenuItem): Boolean {
        when(p0.itemId){
            /*R.id.home_fragment ->{
                homeFragment = HomeFragment()
                supportFragmentManager.beginTransaction().replace(R.id.container,homeFragment).commit()
            }
            R.id.news_fragment ->{
                newsFragment = NewsFragment()
                supportFragmentManager.beginTransaction().replace(R.id.container,newsFragment).commit()
            }
            R.id.mypage_fragment ->{
                myPageFragment = MyPageFragment()
                supportFragmentManager.beginTransaction().replace(R.id.container,myPageFragment).commit()
            }*/
        }
        return true
    }

}