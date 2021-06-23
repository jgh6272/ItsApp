package com.example.itsapp.view.activity

import android.app.Dialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AlertDialog
import com.example.itsapp.view.fragment.HomeFragment
import com.example.itsapp.view.fragment.MyPageFragment
import com.example.itsapp.view.fragment.IssueFragment
import com.example.itsapp.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_home.*

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
        /*val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_user_info)
        dialog.window!!.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT)
        dialog.setCanceledOnTouchOutside(true)
        dialog.setCancelable(true)
        dialog.show()*/

        val dialog2 = AlertDialog.Builder(this)
        dialog2.setView(R.layout.dialog_user_info)
        dialog2.show()

    }

}