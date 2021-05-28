package com.example.itsapp.view.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.itsapp.*
import kotlinx.android.synthetic.main.fragment_home.view.*

class HomeFragment : Fragment() {
    companion object{
        const val TAG : String = "로그"
        fun newInstance() : HomeFragment{
            return HomeFragment()
        }
    }

//    // 메모리에 올라갔을때
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        Log.d(TAG, "HomeFragment -onCreate() called")
//    }
//
//    override fun onAttach(context: Context) {
//        super.onAttach(context)
//        Log.d(TAG, "HomeFragment -onAttach() called")
//    }

    // 뷰가 생성됐을 때
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_home,container,false)

        view.apple_cardview.setOnClickListener{
            val intent = Intent(context, AppleActivity::class.java)
            startActivity(intent)
        }
        view.lg_cardview.setOnClickListener {
            val intent = Intent(context, LgActivity::class.java)
            startActivity(intent)
        }
        view.samsung_cardview.setOnClickListener {
            val intent = Intent(context, SamsungActivity::class.java)
            startActivity(intent)
        }
        view.dell_cardview.setOnClickListener {
            val intent = Intent(context, DellActivity::class.java)
            startActivity(intent)
        }
        view.lenovo_cardview.setOnClickListener {
            val intent = Intent(context, LenovoActivity::class.java)
            startActivity(intent)
        }
        view.asus_cardview.setOnClickListener {
            val intent = Intent(context, AsusActivity::class.java)
            startActivity(intent)
        }
        return view
    }
}