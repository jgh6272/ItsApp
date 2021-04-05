package com.example.itsapp

import android.app.Application
import com.kakao.sdk.common.KakaoSdk

class GlobalApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        KakaoSdk.init(this, "여기에 NATIVE APP KEY 복붙")
    }
}