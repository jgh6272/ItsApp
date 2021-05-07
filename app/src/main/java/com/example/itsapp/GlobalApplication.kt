package com.example.itsapp

import android.app.Application
import com.kakao.sdk.common.KakaoSdk
import dagger.hilt.android.AndroidEntryPoint

class GlobalApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        KakaoSdk.init(this, getString(R.string.kakao_app_key))
    }
}