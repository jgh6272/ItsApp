package com.example.itsapp

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ServiceApi {
    @FormUrlEncoded
    @POST("/android/join")
    fun join(
        @Field("userId") userId : String,
        @Field("password") password : String,
        @Field("userName") userName : String,
        @Field("userNickName") userNickName : String
    ) : Call<String>
}