package com.example.itsapp

import com.google.gson.annotations.SerializedName

data class JoinData(
    @SerializedName("USER_ID") var userId : String,
    @SerializedName("PASSWORD") var password : String,
    @SerializedName("USER_NAME") var userName : String,
    @SerializedName("USER_NICKNAME") var userNickName : String
)