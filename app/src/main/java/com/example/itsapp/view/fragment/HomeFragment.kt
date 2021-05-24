package com.example.itsapp.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.itsapp.R
import kotlinx.android.synthetic.main.fragment_home.view.*

class HomeFragment : Fragment() {

    private lateinit var laptopFragment: LaptopFragment
    private lateinit var desktopFragment: DesktopFragment
    private lateinit var phoneFragment: PhoneFragment
    private lateinit var tabletFragment: TabletFragment
    private lateinit var watchFragment: WatchFragment
    private lateinit var etcDeviceFragment: EtcDeviceFragment

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

        view.laptop_cardview.setOnClickListener{
            laptopFragment = LaptopFragment.newInstance()
            fragmentManager?.beginTransaction()?.replace(R.id.container,laptopFragment)?.commit()
        }
        view.desktop_cardview.setOnClickListener {
            desktopFragment = DesktopFragment.newInstance()
            fragmentManager?.beginTransaction()?.replace(R.id.container,desktopFragment)?.commit()
        }
        view.phone_cardview.setOnClickListener {
            phoneFragment = PhoneFragment.newInstance()
            fragmentManager?.beginTransaction()?.replace(R.id.container,phoneFragment)?.commit()
        }
        view.tablet_cardview.setOnClickListener {
            tabletFragment = TabletFragment.newInstance()
            fragmentManager?.beginTransaction()?.replace(R.id.container,tabletFragment)?.commit()
        }
        view.watch_cardview.setOnClickListener {
            watchFragment = WatchFragment.newInstance()
            fragmentManager?.beginTransaction()?.replace(R.id.container,watchFragment)?.commit()
        }
        view.etc_cardview.setOnClickListener {
            etcDeviceFragment = EtcDeviceFragment.newInstance()
            fragmentManager?.beginTransaction()?.replace(R.id.container,etcDeviceFragment)?.commit()
        }
        return view
    }
}