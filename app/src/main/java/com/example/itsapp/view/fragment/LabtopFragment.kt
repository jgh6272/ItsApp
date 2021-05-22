package com.example.itsapp.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.itsapp.R

class LabtopFragment : Fragment() {
    companion object{
        const val TAG : String = "로그"

        fun newInstance() : LabtopFragment{
            return LabtopFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_labtop,container,false)
        return view
    }
}