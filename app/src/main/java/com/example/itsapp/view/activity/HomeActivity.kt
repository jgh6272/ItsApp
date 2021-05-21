package com.example.itsapp.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.replace
import com.example.itsapp.view.fragment.HomeFragment
import com.example.itsapp.view.fragment.MyPageFragment
import com.example.itsapp.view.fragment.NewsFragment
import com.example.itsapp.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        bottom_navigation_view.setOnNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(p0: MenuItem): Boolean {
        when(p0.itemId) {
            R.id.action_home ->{
                var homeFragment = HomeFragment()
                supportFragmentManager.beginTransaction().replace(R.id.container,homeFragment).commit()
                return true
            }
            R.id.action_news ->{
                var newsFragment = NewsFragment()
                supportFragmentManager.beginTransaction().replace(R.id.container,newsFragment).commit()
                return true
            }
            R.id.action_mypage ->{
                var myPageFragment = MyPageFragment()
                supportFragmentManager.beginTransaction().replace(R.id.container,myPageFragment).commit()
                return true
            }
        }
        return false
    }
}