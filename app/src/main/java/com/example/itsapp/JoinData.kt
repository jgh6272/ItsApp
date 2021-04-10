package com.example.itsapp

import com.google.gson.annotations.SerializedName

data class JoinData(
    @SerializedName("userId") var userId : String,
    @SerializedName("password") var password : String,
    @SerializedName("userName") var userName : String,
    @SerializedName("userNickName") var userNickName : String
)