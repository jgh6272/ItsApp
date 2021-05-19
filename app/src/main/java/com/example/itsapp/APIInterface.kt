package com.example.itsapp

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface APIInterface {

    /*회원가입*/
    @FormUrlEncoded
    @POST("/android/join")
    suspend fun join(
        @Field("userId") userId: String,
        @Field("userPw") userPw: String,
        @Field("userName") userName: String,
        @Field("userNickname") userNickname : String,
        @Field("loginMethod") loginMethod: String
    ):String

}