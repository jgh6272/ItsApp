package com.example.itsapp

import retrofit2.Call
import retrofit2.http.*

data class ResponseDC(var result:String? = null)
interface APIInterface {
    companion object{
        val BASE_URL: String = "http://ec2-54-180-29-97.ap-northeast-2.compute.amazonaws.com"
    }

    @GET("/")
    fun getRequest(@Query("name") name : String) : Call<ResponseDC>

    @FormUrlEncoded
    @POST("/")
    fun postRequest(@Field("id") id : String,
                    @Field("password") password: String) : Call<ResponseDC>

    @FormUrlEncoded
    @PUT("/{id}")
    fun putRequest(@Path("id")id: String,
                   @Field("content")content: String): Call<ResponseDC>

    @DELETE("/{id}")
    fun deleteRequest(@Path("id")id: String): Call<ResponseDC>
}