package com.example.itsapp.view.fragment

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.itsapp.R
import com.example.itsapp.view.activity.HomeActivity
import com.example.itsapp.view.activity.MainActivity
import com.example.itsapp.viewmodel.HomeViewModel
import com.google.android.material.snackbar.Snackbar
import com.kakao.sdk.common.util.KakaoCustomTabsClient
import com.kakao.sdk.talk.TalkApiClient
import com.kakao.sdk.user.UserApiClient
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_my_page.*
import kotlinx.android.synthetic.main.fragment_my_page.view.*
import kotlin.math.log

class MyPageFragment : Fragment() {

    private val viewModel:HomeViewModel by viewModels()
    private var userNickname:String=""
    private var loginMethod:String=""
    companion object{
        const val TAG : String = "로그"

        fun newInstance() : MyPageFragment{
            return MyPageFragment()
        }
    }

    // 메모리에 올라갔을때
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "MyPageFragment -onCreate() called")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d(TAG, "MyPageFragment -onAttach() called")
    }

    // 뷰가 생성됐을 때
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_my_page,container,false)
        return view
    }

    override fun onStart() {
        super.onStart()
        loginMethod = viewModel.getLoginMethod()!!
        viewModel.userInfo(loginMethod)
        liveData()
        mypage_login_method.text = loginMethod
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /*탈퇴하기 버튼*/
        mypage_retire.setOnClickListener{
            val builder = AlertDialog.Builder(activity)
            builder.setMessage("정말 탈퇴하시겠습니까?")
                .setPositiveButton("탈퇴하기"){dialogInterface, i ->
                    viewModel.retireApp(loginMethod)
                }
                .setNegativeButton("취소"){dialogInterface, i ->

                }
                .show()
        }
        /*문의하기 버튼*/
        inquire_btn.setOnClickListener{
            val url = TalkApiClient.instance.channelChatUrl("_lELGs")
            KakaoCustomTabsClient.openWithDefault(requireContext(),url)
        }
        /*로그아웃 버튼*/
        mypage_logout.setOnClickListener{
            disconnect()
            startActivity(Intent(activity,MainActivity::class.java))
            activity?.overridePendingTransition(R.anim.right_in, R.anim.left_out);
        }
    }
    /*라이브데이터*/
    fun liveData(){
        viewModel.userInfoLiveData.observe(this, Observer {
            userNickname = it.jsonArray.userNickname
            Log.d(TAG, "liveData: $userNickname")
            mypage_nickname.text = userNickname
        })
        viewModel.retireLiveData.observe(this, Observer {
            if(it=="200"){
                disconnect()
                val intent = Intent(activity,MainActivity::class.java)
                intent.putExtra("탈퇴","탈퇴")
                startActivity(intent)
                activity?.overridePendingTransition(R.anim.right_in, R.anim.left_out)
                activity?.finish()
            }else {
                Snackbar.make(home_activity, "회원 탈퇴 오류", Snackbar.LENGTH_SHORT).show()
            }
        })
    }
    /*로그아웃,회원탈퇴시 sharedpreference 쿠키 삭제와 카카오 자동로그인 해제 함수*/
    fun disconnect(){
        viewModel.logoutPref()
        if(loginMethod == "카카오"){
            UserApiClient.instance.logout {error ->
                if(error !=null){
                    Log.e(TAG, "로그아웃 실패. SDK에서 토큰 삭제됨", error)
                }
                else {
                    Log.i(TAG, "로그아웃 성공. SDK에서 토큰 삭제됨")
                }
            }
        }
    }
}