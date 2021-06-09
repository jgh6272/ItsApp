package com.example.itsapp.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.example.itsapp.view.fragment.HomeFragment
import com.example.itsapp.view.fragment.MyPageFragment
import com.example.itsapp.view.fragment.NewsFragment
import com.example.itsapp.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    private lateinit var homeFragment: HomeFragment
    private lateinit var myPageFragment: MyPageFragment
    private lateinit var newsFragment: NewsFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        bottom_navigation_view.setOnNavigationItemSelectedListener(this)



        // onCreate되면서 홈프래그먼트를 add로 바로 띄워준다.
        homeFragment = HomeFragment.newInstance()
        supportFragmentManager.beginTransaction().add(R.id.container,homeFragment).commit()
    }

    override fun onNavigationItemSelected(p0: MenuItem): Boolean {
        when(p0.itemId) {
            R.id.action_home -> {
                homeFragment = HomeFragment.newInstance()
                supportFragmentManager.beginTransaction().replace(R.id.container,homeFragment).commit()
            }
            R.id.action_news ->{
                newsFragment = NewsFragment.newInstance()
                supportFragmentManager.beginTransaction().replace(R.id.container,newsFragment).commit()
            }
            R.id.action_mypage ->{
                myPageFragment = MyPageFragment.newInstance()
                supportFragmentManager.beginTransaction().replace(R.id.container,myPageFragment).commit()
            }
        }
        return true
    }

}