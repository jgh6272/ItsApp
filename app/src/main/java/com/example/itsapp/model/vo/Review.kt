package com.example.itsapp.model.vo

import com.google.gson.annotations.SerializedName

data class Review(
    @SerializedName("DEVICE_NAME") var deviceName: String,
    @SerializedName("WRITER") var writer: String,
    @SerializedName("REVIEW_POINT") var reviewPoint: Int,
    @SerializedName("CONTENT_PROS") var contentPros: String,
    @SerializedName("CONTENT_CONS") var contentCons: String,
    @SerializedName("LIKE_COUNT") var likeCount : Int,
    @SerializedName("WRITE_TIME") var writeTime : String
)