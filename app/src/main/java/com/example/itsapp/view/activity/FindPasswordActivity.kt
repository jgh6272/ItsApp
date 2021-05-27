package com.example.itsapp.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.itsapp.FindPwFragment
import com.example.itsapp.R
import com.example.itsapp.view.FindAdapter
import com.example.itsapp.view.fragment.FindIdFragment
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_find_password.*

class FindPasswordActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_find_password)

        val fragmentAdapter = FindAdapter(supportFragmentManager)
        find_account_container.adapter = fragmentAdapter

        tabs.setupWithViewPager(find_account_container)
    }
}