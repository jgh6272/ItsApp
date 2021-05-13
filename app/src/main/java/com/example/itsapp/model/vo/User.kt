package com.example.itsapp.model.vo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class User(
//    @SerializedName : JSON으로 serialzie 될때 이름을 명시하는 목적으로 사용되는 field 마킹 어노테이션이다.
//    @Expose : object 중 해당 값이 null일 경우, json으로 만들 필드를 자동으로 생략해준다.
    @SerializedName("USER_ID") @Expose var userId:String,
    @SerializedName("PASSWORD") @Expose var password:String,
    @SerializedName("USER_NAME") @Expose var userName:String,
    @SerializedName("USER_NICKNAME") @Expose var userNickname:String
)