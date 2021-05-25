package com.example.itsapp.model.vo

import com.google.gson.annotations.SerializedName

data class UserInfo (
    @SerializedName("code") var code : String,
    @SerializedName("jsonArray") var jsonArray:User
)