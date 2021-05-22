package com.example.itsapp.util

import android.content.Context
import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class AddCookiesInterceptor(context: Context) : Interceptor {

    val prefs:SharedPreference = SharedPreference(context)
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()
        val cookies = prefs.getCookies()

        if(cookies != null){
            for(cookie : String in cookies){
                Log.d("TAG", "intercept: $cookie")
                builder.addHeader("Cookie",cookie)
            }
        }

        //Web,Android,IOs 구분을 위해 User-Agent 셋팅
        builder.removeHeader("User-Agent").addHeader("User-Agent","Android")

        return chain.proceed(builder.build())
    }
}