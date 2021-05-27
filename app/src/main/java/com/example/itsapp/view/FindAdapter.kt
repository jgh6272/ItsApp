package com.example.itsapp.view

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.itsapp.FindPwFragment
import com.example.itsapp.view.fragment.FindIdFragment

class FindAdapter(manager:FragmentManager):FragmentPagerAdapter(manager) {

    override fun getItem(position: Int): Fragment {
        return when(position){
            0->{FindIdFragment()}
            else ->{FindPwFragment()}
        }
    }

    override fun getCount(): Int = 2
    override fun getPageTitle(position: Int): CharSequence? {
        return when (position){
            0 -> "아이디 찾기"
            else -> "비밀번호 찾기"
        }
    }
}