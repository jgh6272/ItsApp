package com.example.itsapp

import retrofit2.http.*

/*data class ResponseDC(var result:String? = null)*/
interface APIInterface {

    companion object{
        val BASE_URL: String = "http://ec2-54-180-29-97.ap-northeast-2.compute.amazonaws.com"
    }

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