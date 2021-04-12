package com.example.itsapp

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    // 서버 주소
    private const val BASE_URL = "http://ec2-13-209-75-229.ap-northeast-2.compute.amazonaws.com:3000"

    private var retrofit : Retrofit? = null

    fun getClient() : Retrofit{
        if(retrofit == null){
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit!!
    }
}