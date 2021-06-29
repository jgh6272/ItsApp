package com.example.itsapp.model.vo

import com.google.gson.annotations.SerializedName

data class Review(
    @SerializedName("DEVICE_BRAND") var deviceBrand : String,
    @SerializedName("DEVICE_NAME") var deviceName: String,
    @SerializedName("USER_NICKNAME") var writer: String,
    @SerializedName("REVIEW_POINT") var reviewPoint: Int,
    @SerializedName("CONTENT_PROS") var contentPros: String,
    @SerializedName("CONTENT_CONS") var contentCons: String,
    @SerializedName("LIKE_COUNT") var likeCount : Int,
    @SerializedName("COMMENT_COUNT") var commentCount : Int,
    @SerializedName("WRITE_TIME") var writeTime : String
)