package com.example.itsapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.activity_show_news.*

class ShowNewsActivity : AppCompatActivity() {

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_news)

        loading_bar.visibility = View.GONE
        web_view.apply {
            webViewClient = WebViewClient()
            settings.javaScriptEnabled=true
        }
        intent.getStringExtra("")
        web_view.loadUrl()
    }
    override fun onBackPressed() {
        if (web_view.canGoBack()) {
            web_view.goBack()
        } else {
            finish()
        }
    }
}