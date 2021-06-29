package com.example.itsapp.util

import android.content.Context
import android.content.SharedPreferences

class SharedPreference(context: Context) {

    val prefs : SharedPreferences =
        context.getSharedPreferences("userInfo",Context.MODE_PRIVATE)

    fun putCookies(cookies : HashSet<String>){
        prefs.edit().putStringSet("cookies",cookies).apply()
    }

    fun getCookies() : MutableSet<String>?{
        return prefs.getStringSet("cookies",HashSet<String>())
    }

    fun removeCookies(){
        prefs.edit().remove("cookies").apply()
    }

    var userId:String?
    get() = prefs.getString("userInfo","")
    set(value) = prefs.edit().putString("userInfo",value).apply()

    var loginMethod :String?
    get() = prefs.getString("loginMethod","")
    set(value) = prefs.edit().putString("loginMethod",value).apply()
}