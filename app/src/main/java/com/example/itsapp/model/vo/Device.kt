package com.example.itsapp.model.vo

import com.google.gson.annotations.SerializedName

data class Device(
    @SerializedName("DEVICE_NAME") var deviceName: String,
    @SerializedName("REVIEW_POINT") var reviewPoint: Double,
    @SerializedName("REVIEW_COUNT") var reviewCount: Int
)
