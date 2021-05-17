package com.example.itsapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.itsapp.HomeFragment
import com.example.itsapp.MyPageFragment
import com.example.itsapp.NewsFragment
import com.example.itsapp.R
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {
    companion object{
        const val HOME_FRAGMENT = 0
        const val NEWS_FRAGMENT = 1
        const val MY_PAGE_FRAGMENT = 2
    }
    private lateinit var fragmentManager: FragmentManager
    private lateinit var  fragmentTransaction: FragmentTransaction
    private lateinit var homeFragment : HomeFragment
    private lateinit var newsFragment: NewsFragment
    private lateinit var myPageFragment: MyPageFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)


    }

    override fun onNavigationItemSelected(p0: MenuItem): Boolean {
        when(p0.itemId){
            R.id.home_fragment ->{
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
            }
        }
        return true
    }

}