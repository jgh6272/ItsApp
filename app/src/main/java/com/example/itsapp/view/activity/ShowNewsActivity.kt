package com.example.itsapp.view.activity

import android.annotation.SuppressLint
import android.app.Dialog
import android.graphics.Bitmap
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
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
import kotlinx.android.synthetic.main.activity_show_news.*

class ShowNewsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_news)

        web_view.apply {
            webViewClient = WebViewClientClass()
        }
        val url = intent.getStringExtra("url")
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
        } else {
            finish()
        }
    }
}