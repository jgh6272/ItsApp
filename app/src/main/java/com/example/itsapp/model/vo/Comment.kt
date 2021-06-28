package com.example.itsapp.model.vo

import com.google.gson.annotations.SerializedName

data class Comment(
    @SerializedName("COMMENT_ID") var commnetId : Int,
    @SerializedName("DEVICE_NAME") var deviceName : String,
    @SerializedName("USER_ID") var reviewWriter : String,
    @SerializedName("USER_NICKNAME") var writer : String,
    @SerializedName("COMMENT_CONTENT") var comment : String,
    @SerializedName("WRITE_TIME") var writeTime : String
)