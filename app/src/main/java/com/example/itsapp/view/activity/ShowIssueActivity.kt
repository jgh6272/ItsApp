package com.example.itsapp.view.activity

import android.annotation.SuppressLint
import android.app.Dialog
import android.graphics.Bitmap
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import androidx.activity.viewModels
import com.example.itsapp.R
import com.example.itsapp.viewmodel.LoginViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_show_news.*

class ShowIssueActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_news)

        web_view.apply {
            webViewClient = WebViewClientClass()
            //팝업이나 파일 업로드 등 설정해주기 위해 webView.webChromeClient를 설정
            // 웹뷰에서 크롬이 실행가능&& 새창띄우기는 안됨
            // webChromeClient = WebChromeClient()

            //웹뷰에서 팝업창 호출하기 위해
            webChromeClient = object : WebChromeClient() {
                @SuppressLint("SetJavaScriptEnabled")
                override fun onCreateWindow(view: WebView?, isDialog: Boolean, isUserGesture: Boolean, resultMsg: Message?): Boolean {
                    val newWebView = WebView(this@ShowIssueActivity).apply {
                        webViewClient = WebViewClient()
                        settings.javaScriptEnabled = true
                    }
                    (resultMsg?.obj as WebView.WebViewTransport).webView = newWebView
                    resultMsg.sendToTarget()
                    return true
                }
            }


            settings.javaScriptEnabled = true
            settings.setSupportMultipleWindows(true) // 새창띄우기 허용여부
            settings.javaScriptCanOpenWindowsAutomatically = true // 자바스크립트 새창뛰우기 (멀티뷰) 허용여부
            settings.loadWithOverviewMode = true //메타태크 허용여부
            settings.useWideViewPort = true //화면 사이즈 맞추기 허용여부
            settings.setSupportZoom(true) //화면 줌 허용여부
            settings.builtInZoomControls = true //화면 확대 축소 허용여부

            // Enable and setup web view cache
            settings.cacheMode =
                WebSettings.LOAD_NO_CACHE //브라우저 캐시 허용여부  // WebSettings.LOAD_DEFAULT
            settings.domStorageEnabled = true //로컬저장소 허용여부
            settings.displayZoomControls = true

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                settings.safeBrowsingEnabled = true  // api 26
            }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                settings.mediaPlaybackRequiresUserGesture = false
            }

            settings.allowContentAccess = true
            settings.setGeolocationEnabled(true)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                settings.allowUniversalAccessFromFileURLs = true
            }

            settings.allowFileAccess = true
            //settings.loadsImagesAutomatically = true

            fitsSystemWindows = true
        }
        val url = intent.getStringExtra("url")
        Log.d("TAG", "onCreate: "+url)
        if (url != null) {
            web_view.loadUrl(url)
        }
    }
    inner class WebViewClientClass:WebViewClient(){
        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
            view.loadUrl(url)
            return true
        }

        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            super.onPageStarted(view, url, favicon)
            loading_bar.visibility = ProgressBar.VISIBLE
            web_view.visibility = View.INVISIBLE
        }

        override fun onPageCommitVisible(view: WebView?, url: String?) {
            super.onPageCommitVisible(view, url)
            loading_bar.visibility = ProgressBar.GONE
            web_view.visibility = View.VISIBLE
        }
    }
    override fun onBackPressed() {
        if (web_view.canGoBack()) {
            web_view.goBack()
            Snackbar.make(show_issue_activity,"뒤로 가기를 한번 더 누르시면 Issue 목록으로 이동합니다.",Snackbar.LENGTH_SHORT).show()
        } else {
            finish()
        }
    }
}