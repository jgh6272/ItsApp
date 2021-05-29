package com.example.itsapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.itsapp.view.fragment.NewsFragment
import kotlinx.android.synthetic.main.activity_apple.*
import kotlinx.android.synthetic.main.activity_apple.rv_device
import kotlinx.android.synthetic.main.fragment_apple.*

class AppleFragment : Fragment() {

    lateinit var deviceAdapter: DeviceAdapter

    companion object{
        const val TAG : String = "로그"

        fun newInstance() : AppleFragment {
            return AppleFragment()
        }
    }

    // 메모리에 올라갔을때
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    // 뷰가 생성됐을 때
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_apple,container,false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val deviceList = arrayListOf(
            Device(R.drawable.macbook,"Mac Book Pro 16인치"),
            Device(R.drawable.macbook,"Mac Book Pro 16인치"),
            Device(R.drawable.macbook,"Mac Book Pro 16인치"),
            Device(R.drawable.macbook,"Mac Book Pro 16인치"),
            Device(R.drawable.macbook,"Mac Book Pro 16인치"),
            Device(R.drawable.macbook,"Mac Book Pro 16인치"),
            Device(R.drawable.macbook,"Mac Book Pro 16인치"),
            Device(R.drawable.macbook,"Mac Book Pro 16인치"),
            Device(R.drawable.macbook,"Mac Book Pro 16인치"),
            Device(R.drawable.macbook,"Mac Book Pro 16인치")
        )
        rv_device.layoutManager = GridLayoutManager(activity,2)
        rv_device.adapter = DeviceAdapter(deviceList)
    }
}