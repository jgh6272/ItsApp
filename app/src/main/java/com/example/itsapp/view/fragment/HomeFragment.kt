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

    private lateinit var appleFragment: AppleFragment
    private lateinit var lgFragment: LgFragment
    private lateinit var samsungFragment: SamsungFragment
    private lateinit var dellFragment: DellFragment
    private lateinit var lenovoFragment: LenovoFragment
    private lateinit var asusFragment: AsusFragment

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
//            val intent = Intent(context, AppleActivity::class.java)
//            startActivity(intent)
            appleFragment = AppleFragment.newInstance()
            fragmentManager?.beginTransaction()?.replace(R.id.container,appleFragment)?.commit()
        }
        view.lg_cardview.setOnClickListener {
            lgFragment = LgFragment.newInstance()
            fragmentManager?.beginTransaction()?.replace(R.id.container,lgFragment)?.commit()
        }
        view.samsung_cardview.setOnClickListener {
            samsungFragment = SamsungFragment.newInstance()
            fragmentManager?.beginTransaction()?.replace(R.id.container,samsungFragment)?.commit()
        }
        view.dell_cardview.setOnClickListener {
            dellFragment = DellFragment.newInstance()
            fragmentManager?.beginTransaction()?.replace(R.id.container,dellFragment)?.commit()
        }
        view.lenovo_cardview.setOnClickListener {
            lenovoFragment = LenovoFragment.newInstance()
            fragmentManager?.beginTransaction()?.replace(R.id.container,lenovoFragment)?.commit()
        }
        view.asus_cardview.setOnClickListener {
            asusFragment = AsusFragment.newInstance()
            fragmentManager?.beginTransaction()?.replace(R.id.container,asusFragment)?.commit()
        }
        return view
    }
}