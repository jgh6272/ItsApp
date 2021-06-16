package com.example.itsapp.model.vo

import com.google.gson.annotations.SerializedName

data class Device(
    @SerializedName("DEVICE_NAME") var deviceName: String,
    @SerializedName("DEVICE_BRAND") var deviceBrand: String,
    @SerializedName("REVIEW_POINT") var reviewPoint: Float,
    @SerializedName("REVIEW_COUNT") var reviewCount: Int,
    @SerializedName("DEVICE_PRICE") var devicePrice: String,
    @SerializedName("REVIEW_POINT_5_COUNT") var reviewPoint5Count :Int,
    @SerializedName("REVIEW_POINT_4_COUNT") var reviewPoint4Count :Int,
    @SerializedName("REVIEW_POINT_3_COUNT") var reviewPoint3Count :Int,
    @SerializedName("REVIEW_POINT_2_COUNT") var reviewPoint2Count :Int,
    @SerializedName("REVIEW_POINT_1_COUNT") var reviewPoint1Count :Int
)