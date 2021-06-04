package com.example.itsapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.itsapp.model.vo.Device
import com.example.itsapp.viewmodel.DeviceViewModel
import kotlinx.android.synthetic.main.fragment_apple.*

class AppleFragment : Fragment() {

    val deviceList = arrayListOf<Device>()
    val deviceAdapter = DeviceAdapter(deviceList)
    private val viewModel: DeviceViewModel by viewModels()

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

        rv_device.layoutManager = GridLayoutManager(activity,2)
        rv_device.adapter = deviceAdapter

        deviceList.add(Device("Mac Book Pro",5.4,20))


        viewModel.getDevice()

        deviceAdapter.setItemClickListener(object : DeviceAdapter.OnItemClickListener{
            override fun onClick(v: View, position: Int) {
                Toast.makeText(activity,"${deviceList[position].deviceName}",Toast.LENGTH_SHORT).show()
                if(position == 0) {
                    val intent = Intent(context, ReviewActivity::class.java)
                    startActivity(intent)
                }
            }
        })
    }
}