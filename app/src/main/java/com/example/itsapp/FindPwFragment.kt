package com.example.itsapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.itsapp.view.fragment.HomeFragment

class FindPwFragment : Fragment() {

    companion object{
        fun newInstance() : FindPwFragment {
            return FindPwFragment()
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}