package com.example.itsapp.model.vo

import com.google.gson.annotations.SerializedName

data class userDetail(
    @SerializedName("USER_ID") var userId :String,
    @SerializedName("USER_SEX") var userSex:String,
    @SerializedName("USER_AGE") var userAge:String,
    @SerializedName("USER_JOB") var userJob:String,
    @SerializedName("PARTICIPATION") var participation:String
)
data class userDetailInfo(
    @SerializedName("code") var code :String,
    @SerializedName("jsonArray") var jsonArray:userDetail
)