package com.example.itsapp.view.activity

import android.app.Dialog
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.Paint
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import android.widget.EditText
import android.widget.NumberPicker
import androidx.appcompat.app.AlertDialog
import com.example.itsapp.view.fragment.HomeFragment
import com.example.itsapp.view.fragment.MyPageFragment
import com.example.itsapp.view.fragment.IssueFragment
import com.example.itsapp.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.dialog_user_info.*

class HomeActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    private lateinit var homeFragment: HomeFragment
    private lateinit var myPageFragment: MyPageFragment
    private lateinit var issueFragment: IssueFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        bottom_navigation_view.setOnNavigationItemSelectedListener(this)

        // onCreate되면서 홈프래그먼트를 add로 바로 띄워준다.
        homeFragment = HomeFragment.newInstance()
        supportFragmentManager.beginTransaction().add(R.id.container,homeFragment).commit()
        dialog()
    }

    override fun onNavigationItemSelected(p0: MenuItem): Boolean {
        when(p0.itemId) {
            R.id.action_home -> {
                homeFragment = HomeFragment.newInstance()
                supportFragmentManager.beginTransaction().replace(R.id.container,homeFragment).commit()
            }
            R.id.action_news ->{
                issueFragment = IssueFragment.newInstance()
                supportFragmentManager.beginTransaction().replace(R.id.container,issueFragment).commit()
            }
            R.id.action_mypage ->{
                myPageFragment = MyPageFragment.newInstance()
                supportFragmentManager.beginTransaction().replace(R.id.container,myPageFragment).commit()
            }
        }
        return true
    }

    fun dialog(){
        val data:Array<String> = Array(82,{""})
        for(i in 1..81){
            data[i] = i.toString()
        }
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_user_info)
        dialog.window!!.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT)
        dialog.setCanceledOnTouchOutside(true)
        dialog.setCancelable(true)
        dialog.show()

        dialog.dialog_finish.setOnClickListener{
            dialog.dismiss()
        }
        dialog.age_picker.minValue = 1
        dialog.age_picker.maxValue = 80
        dialog.age_picker.wrapSelectorWheel = false
        dialog.age_picker.displayedValues =data
        dialog.age_picker.setBackgroundColor(Color.argb(0, 0, 0, 0))
        setNumberPickerTextColor(dialog.age_picker,Color.argb(255, 255, 255, 255))
    }
    private fun setNumberPickerTextColor(numberPicker: NumberPicker, color: Int){
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
            val count = numberPicker.childCount
            for (i in 0..count) {
                val child = numberPicker.getChildAt(i)
                if (child is EditText) {
                    try {
                        child.setTextColor(color)
                        numberPicker.invalidate()
                        var selectorWheelPaintField = numberPicker.javaClass.getDeclaredField("mSelectorWheelPaint")
                        var accessible = selectorWheelPaintField.isAccessible
                        selectorWheelPaintField.isAccessible = true
                        (selectorWheelPaintField.get(numberPicker) as Paint).color = color
                        selectorWheelPaintField.isAccessible = accessible
                        numberPicker.invalidate()
                        var selectionDividerField = numberPicker.javaClass.getDeclaredField("mSelectionDivider")
                        accessible = selectionDividerField.isAccessible
                        selectionDividerField.isAccessible = true
                        selectionDividerField.set(numberPicker, null)
                        selectionDividerField.isAccessible = accessible
                        numberPicker.invalidate()
                    } catch (exception: Exception) {
                        Log.d("test", "exception $exception")
                    }
                }
            }
        } else {
            numberPicker.textColor = color
        }
    }

}