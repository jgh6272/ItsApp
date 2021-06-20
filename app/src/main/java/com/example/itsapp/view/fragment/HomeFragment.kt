package com.example.itsapp.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.itsapp.*
import kotlinx.android.synthetic.main.fragment_home.view.*

class HomeFragment : Fragment() {

    private var deviceFragment = DeviceFragment()

    companion object{
        const val TAG : String = "로그"
        fun newInstance() : HomeFragment{
            return HomeFragment()
        }
    }

    // 뷰가 생성됐을 때
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_home,container,false)

        // 브랜드별 프래그먼트들을 만들지 말고 하나만 만들어서
        // 클릭된게 apple_cardview 이면 브랜드 프래그먼트에 Apple 이라는 걸 bundle 객체를 생성해(key와 value) 저장한다음
        // 브랜드 프래그먼트로 전달한다.
        view.apple_cardview.setOnClickListener{
            var bundle = Bundle()
            bundle.putString("deviceBrand","Apple")
            deviceFragment.arguments = bundle
            Log.d("putString", bundle.toString())
            activity?.supportFragmentManager!!.beginTransaction().replace(R.id.container,deviceFragment).commit()
        }
        view.lg_cardview.setOnClickListener {
            var bundle = Bundle()
            bundle.putString("deviceBrand","LG")
            deviceFragment.arguments = bundle
            activity?.supportFragmentManager!!.beginTransaction().replace(R.id.container,deviceFragment).commit()
        }
        view.samsung_cardview.setOnClickListener {
            var bundle = Bundle()
            bundle.putString("deviceBrand","SAMSUNG")
            deviceFragment.arguments = bundle
            activity?.supportFragmentManager!!.beginTransaction().replace(R.id.container,deviceFragment).commit()
        }
        view.dell_cardview.setOnClickListener {
            var bundle = Bundle()
            bundle.putString("deviceBrand","DELL")
            deviceFragment.arguments = bundle
            activity?.supportFragmentManager!!.beginTransaction().replace(R.id.container,deviceFragment).commit()
        }
        view.lenovo_cardview.setOnClickListener {
            var bundle = Bundle()
            bundle.putString("deviceBrand","LENOVO")
            deviceFragment.arguments = bundle
            activity?.supportFragmentManager!!.beginTransaction().replace(R.id.container,deviceFragment).commit()
        }
        view.asus_cardview.setOnClickListener {
            var bundle = Bundle()
            bundle.putString("deviceBrand","ASUS")
            deviceFragment.arguments = bundle
            activity?.supportFragmentManager!!.beginTransaction().replace(R.id.container,deviceFragment).commit()
        }
        return view
    }
}