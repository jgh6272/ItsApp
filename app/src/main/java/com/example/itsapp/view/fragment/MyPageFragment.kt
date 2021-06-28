package com.example.itsapp.view.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
    private var userId:String=""
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
        mypage_retire.setOnClickListener{
            viewModel.retireApp(loginMethod)
        }
        inquire_btn.setOnClickListener{
            val url = TalkApiClient.instance.channelChatUrl("_lELGs")
            KakaoCustomTabsClient.openWithDefault(requireContext(),url)
        }
        mypage_logout.setOnClickListener{
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
            startActivity(Intent(activity,MainActivity::class.java))
            activity?.overridePendingTransition(R.anim.right_in, R.anim.left_out);
        }
    }
    fun liveData(){
        viewModel.userInfoLiveData.observe(this, Observer {
            userNickname = it.jsonArray.userNickname
            Log.d(TAG, "liveData: $userNickname")
            mypage_nickname.text = userNickname
        })
        viewModel.retireLiveData.observe(this, Observer {
            if(it=="200"){
                Snackbar.make(home_activity, "회원 탈퇴 완료.", Snackbar.LENGTH_SHORT).show()
                startActivity(Intent(activity,MainActivity::class.java))
                activity?.overridePendingTransition(R.anim.right_in, R.anim.left_out);
            }else {
                Snackbar.make(home_activity, "회원 탈퇴 완료.", Snackbar.LENGTH_SHORT).show()
            }
        })
    }
}