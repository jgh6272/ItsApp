package com.example.itsapp.view.activity

import android.app.Dialog
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import android.widget.CompoundButton
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import com.example.itsapp.R
import com.example.itsapp.view.fragment.HomeFragment
import com.example.itsapp.view.fragment.IssueFragment
import com.example.itsapp.view.fragment.MyPageFragment
import com.example.itsapp.viewmodel.HomeViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.dialog_user_info.*


class HomeActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    private lateinit var homeFragment: HomeFragment
    private lateinit var myPageFragment: MyPageFragment
    private lateinit var issueFragment: IssueFragment
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        bottom_navigation_view.setOnNavigationItemSelectedListener(this)

        // onCreate되면서 홈프래그먼트를 add로 바로 띄워준다.
        homeFragment = HomeFragment.newInstance()
        supportFragmentManager.beginTransaction().add(R.id.container,homeFragment).commit()
        viewModel.surveyParticipation()

        liveData()
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

    fun dialog() {
        var sex = ""
        var age:String = ""
        var job:String = ""
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_user_info)
        dialog.window!!.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        dialog.setCanceledOnTouchOutside(true)
        dialog.setCancelable(true)
        dialog.show()

        dialog.dialog_finish.setOnClickListener {
            dialog.dismiss()
        }
        var listener = CompoundButton.OnCheckedChangeListener{ buttonView, isChecked ->
            if(isChecked) {
                when (buttonView.id) {
                    R.id.sex_man -> {
                        dialog.sex_man.setTextColor(Color.argb(255, 255, 255, 255))
                        dialog.sex_woman.setTextColor(Color.argb(255, 0, 0, 0))
                        sex = "m"
                    }
                    R.id.sex_woman -> {
                        dialog.sex_woman.setTextColor(Color.argb(255, 255, 255, 255))
                        dialog.sex_man.setTextColor(Color.argb(255, 0, 0, 0))
                        sex = "w"
                    }
                    R.id.dialog_checkbox_student -> {
                        dialog.dialog_checkbox_student.setTextColor(Color.argb(255, 255, 255, 255))
                        dialog.dialog_checkbox_developer.setTextColor(Color.argb(255, 0, 0, 0))
                        dialog.dialog_checkbox_designer.setTextColor(Color.argb(255, 0, 0, 0))
                        dialog.dialog_checkbox_office.setTextColor(Color.argb(255, 0, 0, 0))
                        dialog.dialog_checkbox_etc.setTextColor(Color.argb(255, 0, 0, 0))
                        job="학생"
                    }
                    R.id.dialog_checkbox_developer -> {
                        dialog.dialog_checkbox_developer.setTextColor(Color.argb(255, 255, 255, 255))
                        dialog.dialog_checkbox_student.setTextColor(Color.argb(255, 0, 0, 0))
                        dialog.dialog_checkbox_designer.setTextColor(Color.argb(255, 0, 0, 0))
                        dialog.dialog_checkbox_office.setTextColor(Color.argb(255, 0, 0, 0))
                        dialog.dialog_checkbox_etc.setTextColor(Color.argb(255, 0, 0, 0))
                        job="개발자"
                    }
                    R.id.dialog_checkbox_designer -> {
                        dialog.dialog_checkbox_designer.setTextColor(Color.argb(255, 255, 255, 255))
                        dialog.dialog_checkbox_student.setTextColor(Color.argb(255, 0, 0, 0))
                        dialog.dialog_checkbox_developer.setTextColor(Color.argb(255, 0, 0, 0))
                        dialog.dialog_checkbox_office.setTextColor(Color.argb(255, 0, 0, 0))
                        dialog.dialog_checkbox_etc.setTextColor(Color.argb(255, 0, 0, 0))
                        job = "디자이너"
                    }
                    R.id.dialog_checkbox_office -> {
                        dialog.dialog_checkbox_office.setTextColor(Color.argb(255, 255, 255, 255))
                        dialog.dialog_checkbox_student.setTextColor(Color.argb(255, 0, 0, 0))
                        dialog.dialog_checkbox_developer.setTextColor(Color.argb(255, 0, 0, 0))
                        dialog.dialog_checkbox_designer.setTextColor(Color.argb(255, 0, 0, 0))
                        dialog.dialog_checkbox_etc.setTextColor(Color.argb(255, 0, 0, 0))
                        job = "사무직"
                    }
                    R.id.dialog_checkbox_etc -> {
                        dialog.dialog_checkbox_etc.setTextColor(Color.argb(255, 255, 255, 255))
                        dialog.dialog_checkbox_student.setTextColor(Color.argb(255, 0, 0, 0))
                        dialog.dialog_checkbox_developer.setTextColor(Color.argb(255, 0, 0, 0))
                        dialog.dialog_checkbox_designer.setTextColor(Color.argb(255, 0, 0, 0))
                        dialog.dialog_checkbox_office.setTextColor(Color.argb(255, 0, 0, 0))
                        job = "사무직"
                    }
                }
            }
        }
        dialog.sex_man.setOnCheckedChangeListener(listener)
        dialog.sex_woman.setOnCheckedChangeListener(listener)
        dialog.dialog_checkbox_student.setOnCheckedChangeListener(listener)
        dialog.dialog_checkbox_developer.setOnCheckedChangeListener(listener)
        dialog.dialog_checkbox_designer.setOnCheckedChangeListener(listener)
        dialog.dialog_checkbox_office.setOnCheckedChangeListener(listener)
        dialog.dialog_checkbox_etc.setOnCheckedChangeListener(listener)
        dialog.dialog_ok_btn.setOnClickListener{
            age = dialog.dialog_age.text.toString()
            if(sex!=""&&age!=""&&job!=""){
                viewModel.userJob(sex,age,job)
                dialog.dismiss()
            }
        }
    }
    fun liveData(){
        viewModel.userLiveData.observe(this, Observer {
            if(it.equals("200")){
                Snackbar.make(home_activity,"참여해주셔서 감사합니다.",Snackbar.LENGTH_SHORT).show()
            }else{
                Snackbar.make(home_activity,"오류",Snackbar.LENGTH_SHORT).show()
            }
        })
        viewModel.participationLiveData.observe(this, Observer {
            if(it.code.equals("200")){
                dialog()
            }else{
                Log.d("TAG", "liveData: 유저 정보 패스")
            }
        })
    }
}