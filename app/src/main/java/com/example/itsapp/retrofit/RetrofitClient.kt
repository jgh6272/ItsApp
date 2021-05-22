package com.example.itsapp.retrofit

import android.content.Context
import com.example.itsapp.util.AddCookiesInterceptor
import com.example.itsapp.util.ReceivedCookiesInterceptor
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {

    private var instance: Retrofit? = null

    fun getInstance(context: Context):Retrofit{
        if(instance ==null){
            val gson = GsonBuilder()
                .setLenient()
                .create()
            val okHttpClient = OkHttpClient.Builder()
                .addNetworkInterceptor(AddCookiesInterceptor(context))
                .addNetworkInterceptor(ReceivedCookiesInterceptor(context))
                .connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .build()
            instance = Retrofit.Builder()
                .baseUrl("http://ec2-54-180-29-97.ap-northeast-2.compute.amazonaws.com:3000")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build()
        }
        return instance!!
   }
}