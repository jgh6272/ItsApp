package com.example.itsapp.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.itsapp.Brand
import com.example.itsapp.BrandAdapter
import com.example.itsapp.R
import kotlinx.android.synthetic.main.fragment_laptop.*

class LaptopFragment : Fragment() {
    companion object{
        const val TAG : String = "로그"
        fun newInstance() : LaptopFragment{
            return LaptopFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_laptop,container,false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val brandList = arrayListOf(
            Brand(R.drawable.ic_baseline_laptop_24,"Apple MacBook","맥북 프로"),
            Brand(R.drawable.ic_baseline_laptop_24,"LG","LG 노트북"),
            Brand(R.drawable.ic_baseline_laptop_24,"SAMSUNG","삼성 노트북"),
            Brand(R.drawable.ic_baseline_laptop_24,"DELL","델 노트북"),
            Brand(R.drawable.ic_baseline_laptop_24,"ASUS","아서스 노트북"),
            Brand(R.drawable.ic_baseline_laptop_24,"MSI","MSI 노트북")
        )
        rv_brand.layoutManager = LinearLayoutManager(activity,LinearLayoutManager.VERTICAL,false)
        rv_brand.adapter = BrandAdapter(brandList)
    }
}