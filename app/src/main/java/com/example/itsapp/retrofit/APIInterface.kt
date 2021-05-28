package com.example.itsapp.retrofit

import com.example.itsapp.model.vo.User
import com.example.itsapp.model.vo.UserInfo
import retrofit2.http.*

interface APIInterface {

    /*회원가입*/
    @FormUrlEncoded
    @POST("/android/join")
    suspend fun join(
        @Field("userId") userId: String,
        @Field("userPw") userPw: String,
        @Field("userName") userName: String,
        @Field("userNickName") userNickname : String,
        @Field("loginMethod") loginMethod: String
    ):String

    /*아이디 중복 검사*/
    @GET("/android/checkId")
    suspend fun checkId(@Query("userId") userId:String):String

    /*닉네임 중복 검사*/
    @GET("/android/checkNick")
    suspend fun checkNick(@Query("userNickName") userNickName: String):String

    /*로그인*/
    @GET("/android/userInfo")
    suspend fun userInfo(@Query("userId") userId: String):UserInfo

    /*Kakao 로그인*/
    @FormUrlEncoded
    @POST("/android/kakaoLogin")
    suspend fun kakaoLogin(
        @Field("userId") userId:String,
        @Field("userName") userName:String
    ):String

    /*유저 정보 검사*/
    @GET("/android/seceondJoin")
    suspend fun seceondJoin(
        @Query("userId") userId:String
    ):String

    /*카카오 닉네임 설정*/
    @FormUrlEncoded
    @POST("/android/kakaoUserInfo")
    suspend fun kakaoUserInfo(
        @Field("userId") userId:String,
        @Field("userNickname") userNickname: String
    ):String

    /*비밀번호 변경*/
    @FormUrlEncoded
    @POST("/android/updatePw")
    suspend fun updatePw(@Field("userId") userID:String,@Field("userPw") userPw:String):String
}