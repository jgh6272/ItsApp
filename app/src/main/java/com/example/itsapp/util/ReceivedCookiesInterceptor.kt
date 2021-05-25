package com.example.itsapp.util

import android.content.Context
import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class ReceivedCookiesInterceptor(context:Context) :Interceptor{
    val prefs:SharedPreference = SharedPreference(context)
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalResponse = chain.proceed(chain.request())
        Log.d("checkCookie", "Cookie :${originalResponse.headers("Set-Cookie")}  ")
        if(!originalResponse.headers("Set-Cookie").isEmpty()){
            val cookies:HashSet<String> = HashSet<String>()

            for(header:String in originalResponse.headers("Set-Cookie")){
                cookies.add(header)
            }
//        sharedPreferences에 cookies를 넣어주는 작업
            Log.d("TAG", "intercept: $cookies")
            prefs.putCookies(cookies)
        }
        return originalResponse
    }
}