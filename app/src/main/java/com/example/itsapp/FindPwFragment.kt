package com.example.itsapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentContainer
import com.example.itsapp.view.fragment.HomeFragment

class FindPwFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,container:ViewGroup?,
        savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_find_pw,container,false)
    }
}