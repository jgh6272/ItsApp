package com.example.itsapp.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.itsapp.FindPwFragment
import com.example.itsapp.R
import com.example.itsapp.view.fragment.FindIdFragment
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_find_password.*

class FindPasswordActivity : AppCompatActivity() {

    private lateinit var findId:FindIdFragment
    private lateinit var findPw:FindPwFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_find_password)

        findId = FindIdFragment.newInstance()
        supportFragmentManager.beginTransaction().add(R.id.container,findId).commit()


        tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                var pos = tab?.position
                when(pos){
                    0->{
                        findId = FindIdFragment.newInstance()
                        supportFragmentManager.beginTransaction().add(R.id.container,findId).commit()
                    }
                    1->{
                        findPw = FindPwFragment.newInstance()
                        supportFragmentManager.beginTransaction().add(R.id.container,findPw).commit()
                    }
                }
            }
        })
    }
}