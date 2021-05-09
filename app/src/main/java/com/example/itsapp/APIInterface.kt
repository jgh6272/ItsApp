package com.example.itsapp

import retrofit2.Call
import retrofit2.http.*

data class ResponseDC(var result:String? = null)
interface APIInterface {

    /*회원가입*/
    /*@FormUrlEncoded
    @POST("/android/join")
    suspend fun join(
        @Field("userId") userId: String,
        @Field("userPw") userPw: String,
        @Field("userName") userName: String,
        @Field("userNickname") userNickname : String,
        @Field("loginMethod") loginMethod: String
    ):String*/

    //post
    @FormUrlEncoded
    @POST("/test")
    fun postRequest(@Field("id") id: String,
                    @Field("pw") pw: String): Call<ResponseDC>

    //post2
    @POST("/{path}")
    fun testRequest(@Path("path")path: String, @Body parameters: HashMap<String, Any>): Call<ResponseDC>

}