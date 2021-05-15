package com.example.itsapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.itsapp.HomeFragment
import com.example.itsapp.R

class HomeActivity : AppCompatActivity() {
    companion object{
        const val HOME_FRAGMENT = 0
        const val NEWS_FRAGMENT = 1
        const val MY_PAGE_FRAGMENT = 2
    }
    private lateinit var fragmentManager: FragmentManager
    private lateinit var  fragmentTransaction: FragmentTransaction
    private lateinit var homeFragment : HomeFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        fragmentManager = supportFragmentManager
        homeFragment = HomeFragment()
        initFragment(homeFragment)
    }
    private fun initFragment(fragment: Fragment){
        fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.replace(R.id.container,fragment)
        fragmentTransaction.commitAllowingStateLoss()
    }
}