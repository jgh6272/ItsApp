package com.example.itsapp.model.vo

import com.google.gson.annotations.SerializedName

data class DeviceInfo(
    @SerializedName("code") var code : String,
    @SerializedName("jsonArray") var jsonArray:List<Device>
)