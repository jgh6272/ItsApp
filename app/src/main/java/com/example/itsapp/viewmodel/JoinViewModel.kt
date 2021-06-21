package com.example.itsapp.viewmodel

import android.app.Application
import android.os.CountDownTimer
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.itsapp.retrofit.APIInterface
import com.example.itsapp.retrofit.RetrofitClient
import com.example.itsapp.util.MailSender
import com.example.itsapp.util.SharedPreference
import kotlinx.coroutines.launch
import javax.mail.MessagingException
import javax.mail.SendFailedException

class JoinViewModel(application: Application):AndroidViewModel(application) {
    val context = getApplication<Application>().applicationContext
    val service: APIInterface = RetrofitClient.getInstance(context).create(
        APIInterface::class.java)
    val prefs = SharedPreference(application)
    val joinLiveData = MutableLiveData<String>()
    val checkIdLiveData = MutableLiveData<String>()
    val checkNicknameLiveData = MutableLiveData<String>()
    val kakaoLoginLiveData = MutableLiveData<String>()
    val kakaoUserInfoLD = MutableLiveData<String>()
    val userIdLiveData = MutableLiveData<String>()
    val count = MutableLiveData<String>()
    lateinit var countDownTimer:CountDownTimer

    fun join(userId : String, userPw : String , userName : String, userNickname: String, loginMethod:String){
        viewModelScope.launch {
            val data = service.join(userId,userPw,userName,userNickname,loginMethod)
            joinLiveData.value = data
        }
    }
    fun checkId(userId:String){
        viewModelScope.launch {
            val data = service.checkId(userId)
            checkIdLiveData.value = data
        }
    }
    fun checkNick(userNickname: String){
        viewModelScope.launch {
            val data = service.checkNick(userNickname)
            checkNicknameLiveData.value = data
        }
    }
    fun kakaoLogin(userId: String,userName:String){
        viewModelScope.launch {
            val data = service.kakaoLogin(userId,userName)
            kakaoLoginLiveData.value = data
        }
    }
    fun kakaoUserInfo(userId:String, userNickname: String){
        viewModelScope.launch {
            val data = service.kakaoUserInfo(userId,userNickname)
            kakaoUserInfoLD.value =data
        }
    }
    fun getLoginSession():String{
        var userSession = ""
        val iterator = prefs.getCookies()?.iterator()
        if(iterator!=null){
            while(iterator.hasNext()){
                userSession =iterator.next()
                userSession = userSession.split(";")[0].split("=")[1]
                Log.d("userInfo", "getLoginSession: $userSession")
            }
        }else if(iterator==null){
            userIdLiveData.postValue(userSession)
            return userSession
        }
        userIdLiveData.postValue(userSession)
        return userSession
    }
    fun setLoginMethod(value:String){
        prefs.loginMethod = value
    }
    fun getLoginMethod(): String? {
        return prefs.loginMethod
    }
    fun countDown() {
        val MILLISINFUTURE = 180 * 1000 //총 시간 (180초 = 3분)
        val COUNT_DOWN_INTERVAL = 1000 //onTick 메소드를 호출할 간격 (1초)
        countDownTimer = object : CountDownTimer(
            MILLISINFUTURE.toLong(),
            COUNT_DOWN_INTERVAL.toLong()
        ) {
            override fun onTick(millisUntilFinished: Long) {
                val emailAuthCount = millisUntilFinished / 1000
                if (emailAuthCount - emailAuthCount / 60 * 60 >= 10) { //초가 10보다 크면 그냥 출력
                    count.postValue((emailAuthCount / 60).toString() + " : " + (emailAuthCount - emailAuthCount / 60 * 60))
                } else { //초가 10보다 작으면 앞에 '0' 붙여서 같이 출력. ex) 02,03,04...
                    count.postValue((emailAuthCount / 60).toString() + " : 0" + (emailAuthCount - emailAuthCount / 60 * 60))
                }
            }

            override fun onFinish() {
                count.setValue("")
            }
        }.start()
    }
}